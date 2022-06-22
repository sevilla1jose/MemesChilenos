package com.jsevilla.memeschilenos.data.feature.intro

import com.jsevilla.memeschilenos.data.base.BaseUseCaseUniTest
import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class IntroUseCasesUnitTest : BaseUseCaseUniTest() {
    private val getIntroUseCase by inject<IntroUseCase>()

    @Test
    fun `SET INTRO FINISH`() = runBlocking {
        getIntroUseCase.setIntroFinish()
    }

    @Test
    fun `SET DAY OR NIGHT`() = runBlocking {
        getIntroUseCase.setDayNight(isDayOrNight = true)
    }
}
