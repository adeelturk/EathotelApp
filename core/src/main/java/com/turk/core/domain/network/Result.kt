package com.turk.core.domain.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

sealed interface Result<out D, out E: ErrorEntity> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: ErrorEntity>(val error: E):
        Result<Nothing, E>
    data class Loading( val isLoading:Boolean) : Result<Nothing, Nothing>
}


fun <T, R> Response<T>.asResult(transform: (T) -> R,): Result<R, ErrorEntity> {

       return if (this@asResult.isSuccessful) {
            val body = this@asResult.body()
            if (body != null) {
                Result.Success(transform(body))
            } else {
                Result.Error(this@asResult.getHttpErrors())
            }
        }else{

           Result.Error(this@asResult.getHttpErrors())
        }
}





