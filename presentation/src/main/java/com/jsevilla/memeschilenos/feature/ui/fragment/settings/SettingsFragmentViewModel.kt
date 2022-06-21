package com.jsevilla.memeschilenos.feature.ui.fragment.settings

import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import com.jsevilla.memeschilenos.feature.base.BaseViewModel

class SettingsFragmentViewModel(
    private val introUseCase: IntroUseCase
) : BaseViewModel() {
    fun getDayNight(): Boolean = introUseCase.getDayNight()

    fun setDayNight(isDayOrNight: Boolean) = introUseCase.setDayNight(isDayOrNight)
}
