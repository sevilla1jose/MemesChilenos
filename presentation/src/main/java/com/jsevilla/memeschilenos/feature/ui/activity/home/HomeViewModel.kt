package com.jsevilla.memeschilenos.feature.ui.activity.home

import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import com.jsevilla.memeschilenos.feature.base.BaseViewModel

class HomeViewModel(
    private val introUseCase: IntroUseCase
) : BaseViewModel() {
    fun getDayNight(): Boolean = introUseCase.getDayNight()
}
