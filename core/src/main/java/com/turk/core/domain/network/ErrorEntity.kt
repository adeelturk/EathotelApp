package com.turk.core.domain.network

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureErrorEntity] class.
 */
sealed class ErrorEntity {

    object Default : ErrorEntity()
    object NetworkConnection : ErrorEntity()
    object ServerError : ErrorEntity()
    object AuthError : ErrorEntity()
    object Forbidden : ErrorEntity()
    object BadRequest : ErrorEntity()
    object NotFound : ErrorEntity()
    object UnSupportedMediaType : ErrorEntity()
    object MalFormedJson : ErrorEntity()
    object IllegalStateException : ErrorEntity()
    object JsonSyntaxException : ErrorEntity()
    object SocketTimedOutException : ErrorEntity()
    object InternalServerError : ErrorEntity()
    object AndroidError : ErrorEntity()

    data class Unknown(val exception:Throwable) : ErrorEntity()


}
