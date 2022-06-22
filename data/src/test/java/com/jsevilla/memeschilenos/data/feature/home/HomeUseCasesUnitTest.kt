package com.jsevilla.memeschilenos.data.feature.home

import com.jsevilla.memeschilenos.data.base.BaseUseCaseUniTest
import com.jsevilla.memeschilenos.domain.usecase.getListMemesChilenos
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class HomeUseCasesUnitTest : BaseUseCaseUniTest() {
    private val getHomeUseCase by inject<getListMemesChilenos>()

    @Test
    fun `GET LIST MEMES`() = runBlocking {
        getHomeUseCase.invoke(this, "") {
            it.either(::printUseCaseFailure, ::printUseCaseSuccessObject)
        }
    }
}
