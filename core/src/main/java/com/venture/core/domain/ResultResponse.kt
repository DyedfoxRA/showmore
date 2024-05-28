package com.venture.core.domain

sealed interface ResultResponse<out D, out E : BaseError> {

    data object Idle : ResultResponse<Nothing, Nothing>

    data object Loading : ResultResponse<Nothing, Nothing>

    @JvmInline
    value class Success<out D>(val data: D) : ResultResponse<D, Nothing>

    @JvmInline
    value class Error<out E : BaseError>(val error: E) : ResultResponse<Nothing, E>


    fun isLoading() = this is ResultResponse.Loading
    fun isSuccess() = this is ResultResponse.Success
    fun isError() = this is ResultResponse.Error
}