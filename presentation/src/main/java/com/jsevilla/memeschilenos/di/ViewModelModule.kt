package com.jsevilla.memeschilenos.di

import com.jsevilla.memeschilenos.feature.ui.activity.splash.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}
