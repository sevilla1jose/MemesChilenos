package com.jsevilla.memeschilenos.domain.di

import com.jsevilla.memeschilenos.domain.usecase.IntroUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { IntroUseCase(get()) }
}
