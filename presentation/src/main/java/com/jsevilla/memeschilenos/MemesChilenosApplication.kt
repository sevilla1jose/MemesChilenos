package com.jsevilla.memeschilenos

import android.app.Application
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

                )
            )
        }
    }
}
