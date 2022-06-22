package com.jsevilla.memeschilenos.data.feature.home

import com.jsevilla.memeschilenos.data.base.BaseUseCaseUniTest
import com.jsevilla.memeschilenos.domain.usecase.GetListMemesChile
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class HomeUseCasesUnitTest : BaseUseCaseUniTest() {
    private val getListMemesChile by inject<GetListMemesChile>()

    @Test
    fun `GET LIST MEMES`() = runBlocking {
        getListMemesChile.invoke(this, Any()) {
            it.either(::printUseCaseFailure, ::printUseCaseSuccessObject)
        }
    }
}
