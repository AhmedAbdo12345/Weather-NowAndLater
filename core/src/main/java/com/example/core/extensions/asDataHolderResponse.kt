package com.example.core.extensions

import com.example.core.util.DataHolder
import retrofit2.Call
import retrofit2.awaitResponse

suspend fun <T> Call<T>.asDataHolderResponse(): DataHolder<T?> {
    val exception =
        kotlin.runCatching {
            awaitResponse()
        }.onSuccess {
            return DataHolder.Success(it.body())
        }.exceptionOrNull()

    return DataHolder.Fail(requireNotNull(exception))
}