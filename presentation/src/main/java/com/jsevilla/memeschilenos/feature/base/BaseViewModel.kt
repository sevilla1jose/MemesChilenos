package com.jsevilla.memeschilenos.feature.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsevilla.memeschilenos.domain.entity.Failure

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showEmptyView = MutableLiveData(false)
    val showEmptyView: LiveData<Boolean>
        get() = _showEmptyView

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _showErrorCause = MutableLiveData(false)
    val showErrorCause: LiveData<Boolean>
        get() = _showErrorCause

    private val _errorCause = MutableLiveData<Int>()
    val errorCause: LiveData<Int>
        get() = _errorCause

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
            is Failure.UnauthorizedOrForbidden -> setError(1)
            is Failure.None -> setError(2)
            is Failure.NetworkConnectionLostSuddenly -> setError(3)
            is Failure.NoNetworkDetected -> setError(4)
            is Failure.SSLError -> setError(5)
            is Failure.TimeOut -> setError(6)
            is Failure.DataToDomainMapperFailure -> setError(7)
            is Failure.ServerBodyError -> setError(8)
            is Failure.ServiceUncaughtFailure -> setError(9)
        }
        showLoading(false)
        setRefreshing(false)
        shouldShowEmptyView(false)
        showErrorCause(true)
    }

    private fun setError(cause: Int) {
        _errorCause.value = cause
    }
}
