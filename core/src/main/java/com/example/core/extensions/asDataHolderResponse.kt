package com.example.core.extensions

import com.example.core.util.DataHolder
import retrofit2.Response

fun <T> Response<T>.asDataHolderResponse(): DataHolder<T?> {
    return when (this.isSuccessful) {
        true -> {
            DataHolder.Success(body())
        }

        false -> {
            DataHolder.Fail(Exception())
        }
    }
}