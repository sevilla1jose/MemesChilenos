package com.jsevilla.memeschilenos.domain.usecase

import com.jsevilla.memeschilenos.domain.repository.IntroRepository

class IntroUseCase(
    private val introRepository: IntroRepository
) {
    fun getIntro(): Boolean = introRepository.getIntro()

    fun setIntroFinish() = introRepository.setIntroFinish()

    fun getDayNight(): Boolean = introRepository.getDayNight()

    fun setDayNight(isDayOrNight: Boolean) = introRepository.setDayNight(isDayOrNight)
}
