package com.jsevilla.memeschilenos.data.di

import com.jsevilla.memeschilenos.data.network.mapper.home.HomeMapper
import com.jsevilla.memeschilenos.data.network.mapper.home.HomeMapperImpl
import org.koin.dsl.module

val mapperDataModule = module {
    single<HomeMapper> { HomeMapperImpl(get()) }
}
