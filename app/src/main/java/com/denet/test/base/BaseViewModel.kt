package com.denet.test.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

const val TAG_ERROR_VIEWMODEL = "TAG_ERROR_VIEWMODEL"

abstract class BaseViewModel(): ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG_ERROR_VIEWMODEL, exception.message ?: "")
    }
    protected val baseDispatchersIO = Dispatchers.IO + exceptionHandler
}