package com.jsevilla.memeschilenos.data.di

import androidx.room.Room
import com.jsevilla.memeschilenos.data.BuildConfig
import com.jsevilla.memeschilenos.data.local.db.AppDatabase
import com.jsevilla.memeschilenos.data.network.end_point.memes.MemesPoints
import com.jsevilla.memeschilenos.data.network.end_point.memes.MemesPointsImpl
import com.jsevilla.memeschilenos.data.network.remote.ApiClient
import com.jsevilla.memeschilenos.data.network.remote.NetworkHandler
import com.jsevilla.memeschilenos.data.network.utils.ConnectionUtils
import com.jsevilla.memeschilenos.data.network.utils.ConnectionUtilsImpl
import com.jsevilla.memeschilenos.data.network.utils.SupportInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single<ConnectionUtils> {
        ConnectionUtilsImpl(
            androidContext()
        )
    }
    factory { SupportInterceptor() }
    single { NetworkHandler(get()) }

    single(named(name = "SERVICE")) { ApiClient.create(get(), BuildConfig.BaseURL) }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "memes_chilenos_database"
        ).build()
    }

    single<MemesPoints> { MemesPointsImpl(get(), get(named(name = "SERVICE"))) }
}
