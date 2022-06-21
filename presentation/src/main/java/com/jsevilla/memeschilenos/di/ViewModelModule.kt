package com.jsevilla.memeschilenos.di

import com.jsevilla.memeschilenos.feature.ui.activity.home.HomeViewModel
import com.jsevilla.memeschilenos.feature.ui.activity.intro.IntroViewModel
import com.jsevilla.memeschilenos.feature.ui.activity.legal.LegalViewModel
import com.jsevilla.memeschilenos.feature.ui.activity.splash.MainViewModel
import com.jsevilla.memeschilenos.feature.ui.fragment.home.HomeFragmentViewModel
import com.jsevilla.memeschilenos.feature.ui.fragment.search.SearchFragmentViewModel
import com.jsevilla.memeschilenos.feature.ui.fragment.settings.SettingsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { IntroViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { HomeFragmentViewModel() }
    viewModel { SearchFragmentViewModel() }
    viewModel { SettingsFragmentViewModel(get()) }
    viewModel { LegalViewModel(get()) }
}
