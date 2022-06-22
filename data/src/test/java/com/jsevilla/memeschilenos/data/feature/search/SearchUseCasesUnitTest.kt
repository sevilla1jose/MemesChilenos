package com.jsevilla.memeschilenos.data.feature.search

import com.jsevilla.memeschilenos.data.base.BaseUseCaseUniTest
import com.jsevilla.memeschilenos.domain.usecase.GetListSearchMemeUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class SearchUseCasesUnitTest : BaseUseCaseUniTest() {
    private val getListSearchMemeUseCase by inject<GetListSearchMemeUseCase>()

    @Test
    fun `GET LIST SEARCH MEMES`() = runBlocking {
        val params = GetListSearchMemeUseCase.Params(data = "meme")
        getListSearchMemeUseCase.invoke(this, params) {
            it.either(::printUseCaseFailure, ::printUseCaseSuccessObject)
        }
    }
}
