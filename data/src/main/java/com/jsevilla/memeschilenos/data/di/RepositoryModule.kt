package com.jsevilla.memeschilenos.data.di

import com.jsevilla.memeschilenos.data.repository.MemesChileRepositoryImpl
import com.jsevilla.memeschilenos.data.repository.IntroRepositoryImpl
import com.jsevilla.memeschilenos.domain.repository.MemesChileRepository
import com.jsevilla.memeschilenos.domain.repository.IntroRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IntroRepository> { IntroRepositoryImpl(get()) }
    single<MemesChileRepository> { MemesChileRepositoryImpl(get(), get(), get(), get()) }
}
