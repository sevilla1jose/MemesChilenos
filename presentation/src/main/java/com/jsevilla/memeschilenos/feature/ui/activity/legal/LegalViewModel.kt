package com.jsevilla.memeschilenos.feature.ui.activity.legal

import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import com.jsevilla.memeschilenos.feature.base.BaseViewModel

class LegalViewModel(
    private val introUseCase: IntroUseCase
) : BaseViewModel() {
    fun getDayNight(): Boolean = introUseCase.getDayNight()
}
