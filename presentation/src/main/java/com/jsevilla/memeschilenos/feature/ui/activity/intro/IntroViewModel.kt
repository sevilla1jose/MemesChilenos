package com.jsevilla.memeschilenos.feature.ui.activity.intro

import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import com.jsevilla.memeschilenos.feature.base.BaseViewModel

class IntroViewModel(
    private val introUseCase: IntroUseCase
) : BaseViewModel() {

    init {
        showLoading(false)
        shouldShowEmptyView(false)
        showErrorCause(false)
        setRefreshing(false)
    }

    fun setIntroFinish() = introUseCase.setIntroFinish()
}