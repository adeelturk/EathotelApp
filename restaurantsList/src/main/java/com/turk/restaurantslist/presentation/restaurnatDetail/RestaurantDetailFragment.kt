package com.turk.restaurantslist.presentation.restaurnatDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.turk.core.domain.model.Restaurant
import com.turk.core.extensions.getFailureMessage
import com.turk.core.extensions.showSnackBarMsg
import com.turk.core.ui.GeneralAdapter
import com.turk.restaurantslist.BR
import com.turk.restaurantslist.R
import com.turk.restaurantslist.databinding.FragmentRestaurantDetailBinding
import com.turk.restaurantslist.presentation.RestaurantEvent
import com.turk.restaurantslist.presentation.RestaurantViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantDetailFragment : Fragment() {

    private val viewModel: RestaurantViewModel by viewModel()

    private lateinit var binding: FragmentRestaurantDetailBinding

   private val stringDiffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            // If identity matters
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            // If content comparison is same for Strings, return true
            return oldItem == newItem
        }
    }

    private val tagsAdapter =
        GeneralAdapter(BR.tag, R.layout.item_tags, stringDiffUtil)


    private val sameCuisineRestaurantAdapter =
        GeneralAdapter(BR.restaurantData, R.layout.item_detail_page_restaurant_card, Restaurant.DIFF_CALLBACK)

 private val sameAreRestaurantAdapter =
        GeneralAdapter(BR.restaurantData, R.layout.item_detail_page_restaurant_card, Restaurant.DIFF_CALLBACK)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRestaurantDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initUi()
    }

    private fun initUi() {
        val layoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW             // Items arranged in a row
            justifyContent = JustifyContent.FLEX_START    // Align to start
            flexWrap = FlexWrap.WRAP                      // Wrap to next line
        }
        binding.tagsRv.layoutManager = layoutManager
        binding.tagsRv.adapter=tagsAdapter
        binding.cuisineAdapter=sameCuisineRestaurantAdapter
        binding.locationRestAdapter=sameAreRestaurantAdapter
        arguments?.getString("restaurantId")?.run {
            viewModel.onEvent(RestaurantEvent.FetchRestaurantDetailById(this))
        }
    }

    private fun observeData() {

        lifecycleScope.launch {
            lifecycleScope.launch {
                viewModel.restaurantDetailState
                    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect { state ->

                        when (state) {
                            is RestaurantDetailState.Success -> {
                                binding.restaurantData=state.restaurantDetail
                                tagsAdapter.submitList(state.restaurantDetail.labels)
                                sameCuisineRestaurantAdapter.submitList(state.restaurantDetail.otherRestaurantOfSameCuisine)
                                sameAreRestaurantAdapter.submitList(state.restaurantDetail.otherRestaurantInSameArea)
                                binding.notifyChange()
                            }

                            is RestaurantDetailState.Error -> {
                                showSnackBarMsg(binding.root, getFailureMessage(state.error))
                            }

                            is RestaurantDetailState.Loading -> {
                                binding.progressBar.isVisible = state.isLoading
                            }

                            RestaurantDetailState.Nothing -> {}
                        }
                    }
            }

        }

    }

}