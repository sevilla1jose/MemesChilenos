package com.jsevilla.memeschilenos.domain.di

import com.jsevilla.memeschilenos.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    factory { IntroUseCase(get()) }
    factory { GetListMemesChile(get()) }
}
