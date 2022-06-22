package com.jsevilla.memeschilenos

import android.app.Application
import com.jsevilla.memeschilenos.data.di.mapperDataModule
import com.jsevilla.memeschilenos.data.di.networkModule
import com.jsevilla.memeschilenos.data.di.preferencesModule
import com.jsevilla.memeschilenos.data.di.repositoryModule
import com.jsevilla.memeschilenos.di.viewModelModule
import com.jsevilla.memeschilenos.domain.di.useCasesModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class MemesChilenosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MemesChilenosApplication)
            modules(
                arrayListOf(
                    networkModule,
                    preferencesModule,
                    mapperDataModule,
                    repositoryModule,
                    useCasesModule,
                    viewModelModule
                )
            )
        }
    }
}
