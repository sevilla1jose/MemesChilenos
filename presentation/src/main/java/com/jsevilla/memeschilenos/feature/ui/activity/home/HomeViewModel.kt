package com.jsevilla.memeschilenos.feature.ui.activity.home

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsevilla.memeschilenos.domain.entity.Failure
import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import com.jsevilla.memeschilenos.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val introUseCase: IntroUseCase
) : BaseViewModel(), CoroutineScope {

    fun getDayNight(): Boolean = introUseCase.getDayNight()

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _initPage = MutableLiveData<Boolean>()
    val initPage: LiveData<Boolean> = _initPage

    private val runnable = Runnable {
        _initPage.value = true
    }

    fun initProcess() {
        launch(Dispatchers.IO) {
            try {
                Handler(Looper.getMainLooper()).postDelayed(runnable, 5000)
            } catch (e: Exception) {
                handleUseCaseFailureFromBase(Failure.None)
            }
        }
    }
}
