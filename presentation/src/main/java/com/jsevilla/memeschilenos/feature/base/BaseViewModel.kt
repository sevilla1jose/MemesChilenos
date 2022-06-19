package com.jsevilla.memeschilenos.feature.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsevilla.memeschilenos.domain.entity.Failure

abstract class BaseViewModel : ViewModel() {

    // Shows or hide progress loading bar if the have it.
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // Shows or hide and empty view layout if the view have it
    private val _showEmptyView = MutableLiveData(false)
    val showEmptyView: LiveData<Boolean>
        get() = _showEmptyView

    // Shows, hide, init or stop refreshing of Swipe refresh layout if the view have it.
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    // Shows, hide, error message view.
    private val _showErrorCause = MutableLiveData(false)
    val showErrorCause: LiveData<Boolean>
        get() = _showErrorCause

    // The resource default value of the error or any error(Exception, server side, etc).
    private val _errorCause = MutableLiveData<Any>()
    val errorCause: LiveData<Any>
        get() = _errorCause

    protected fun logError(errorMessage: String?) {
        Log.e(this.javaClass.simpleName, errorMessage ?: "error message is null.")
    }

    /*
     * The following functions are just for presentation purposes
     */
    protected fun setRefreshing(refreshValue: Boolean) {
        _isRefreshing.value = refreshValue
    }

    protected fun showLoading(loadingValue: Boolean) {
        _isLoading.value = loadingValue
    }

    protected fun shouldShowEmptyView(show: Boolean?) {
        _showEmptyView.value = show
    }

    protected fun showErrorCause(show: Boolean) {
        _showErrorCause.value = show
    }

    protected fun handleUseCaseFailureFromBase(failure: Failure) {
        when (failure) {
            is Failure.UnauthorizedOrForbidden -> setError("No se puede iniciar sesión, verifique el código ingresado")
            is Failure.None -> setError("None")
            is Failure.NetworkConnectionLostSuddenly -> setError("Connection lost suddenly. Check the wifi or mobile data.")
            is Failure.NoNetworkDetected -> setError("No network detected")
            is Failure.SSLError -> setError("WARNING: SSL Exception")
            is Failure.TimeOut -> setError("Time out.")
            is Failure.ServerBodyError -> setError(failure.message)
            is Failure.DataToDomainMapperFailure -> setError("Data to domain mapper failure: ${failure.mapperException}")
            is Failure.ServiceUncaughtFailure -> setError(failure.uncaughtFailureMessage)
        }
        showLoading(false)
        setRefreshing(false)
        shouldShowEmptyView(false)
        showErrorCause(true)
    }

    private fun setError(cause: Any) {
        if (cause is String) {
            logError(cause)
        }
        _errorCause.value = cause
    }
}
