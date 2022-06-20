package com.jsevilla.memeschilenos.domain.usecase

import com.jsevilla.memeschilenos.domain.repository.IntroRepository

class IntroUseCase(
    private val introRepository: IntroRepository
) {
    fun getIntro(): Boolean = introRepository.getIntro()
    fun setIntroFinish() = introRepository.setIntroFinish()
}
