package com.example.core.util

sealed interface DataHolder<out T : Any?> {

    data class Success<out T : Any?>(val data: T?) : DataHolder<T?>
    data class Fail(val e: Throwable) : DataHolder<Nothing>
    data object Loading : DataHolder<Nothing>
}
