package com.turk.restaurantslist.presentation.restuaurantList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.turk.core.domain.model.Restaurant
import com.turk.core.extensions.getFailureMessage
import com.turk.core.extensions.showSnackBarMsg
import com.turk.core.ui.GeneralAdapter
import com.turk.restaurantslist.BR
import com.turk.restaurantslist.R
import com.turk.restaurantslist.databinding.FragmentRestaurantBinding
import com.turk.restaurantslist.presentation.RestaurantViewModel
import com.turk.restaurantslist.presentation.RestaurantEvent
import com.turk.restaurantslist.presentation.navigation.RestaurantNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantsListFragment : Fragment() {

    private val viewModel: RestaurantViewModel by viewModel()

    private lateinit var binding: FragmentRestaurantBinding

    private val navigator: RestaurantNavController by inject()

    private val restaurantAdapter =
        GeneralAdapter(BR.restaurantData, R.layout.item_restaurant_card, Restaurant.DIFF_CALLBACK)

    private var searchQuery:String=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
        setupSearchField()
    }

    private fun setupSearchField(){

        lifecycleScope.launch {
            binding.searchEt.textChanges()
                .debounce(300) // delay in milliseconds
                .filter { it.isNotEmpty() } // optional: ignore blank input
                .distinctUntilChanged()
                .flowOn(Dispatchers.Default)
                .collectLatest { query ->
                    // Perform search here
                    searchQuery =query
                    viewModel.onEvent(RestaurantEvent.FetchRestaurants(searchQuery))
                }
        }
    }


    fun EditText.textChanges(): Flow<String> = callbackFlow {
        val listener = doOnTextChanged{ text, _, _, _ ->
            trySend(text.toString())
        }
        awaitClose { removeTextChangedListener(listener) }
    }

    private fun setupRecyclerView() {
        binding.restaurantAdapter = restaurantAdapter
        binding.refreshLayout.setOnRefreshListener {
            viewModel.onEvent(RestaurantEvent.FetchRestaurants(searchQuery))
        }

        restaurantAdapter.clickListener={data,view->
            data.id?.run {
                navigator.showRestaurantDetail(this)
            }

        }
    }

    private fun observeData() {

        lifecycleScope.launch {
            lifecycleScope.launch {
                viewModel.restaurantListState
                    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect { state ->

                        when (state) {
                            is RestaurantsListState.Success -> {
                                restaurantAdapter.submitList(state.restaurants)
                            }

                            is RestaurantsListState.Error -> {
                                showSnackBarMsg(binding.root, getFailureMessage(state.error))
                            }

                            is RestaurantsListState.Loading -> {
                                binding.refreshLayout.isRefreshing = state.isLoading
                            }

                            RestaurantsListState.Nothing -> {}
                        }
                    }
            }

        }

    }

}