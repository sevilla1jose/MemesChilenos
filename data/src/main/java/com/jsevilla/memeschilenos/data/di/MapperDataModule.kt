package com.jsevilla.memeschilenos.data.di

import com.jsevilla.memeschilenos.data.network.mapper.memes.MemesMapper
import com.jsevilla.memeschilenos.data.network.mapper.memes.MemesMapperImpl
import org.koin.dsl.module

val mapperDataModule = module {
    single<MemesMapper> { MemesMapperImpl(get()) }
}
