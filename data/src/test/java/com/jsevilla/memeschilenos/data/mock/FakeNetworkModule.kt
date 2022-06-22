package com.jsevilla.memeschilenos.data.mock

import androidx.room.Room
import com.jsevilla.memeschilenos.data.BuildConfig
import com.jsevilla.memeschilenos.data.local.db.AppDatabase
import com.jsevilla.memeschilenos.data.network.end_point.home.HomePoints
import com.jsevilla.memeschilenos.data.network.end_point.home.HomePointsImpl
import com.jsevilla.memeschilenos.data.network.remote.ApiClient
import com.jsevilla.memeschilenos.data.network.remote.NetworkHandler
import com.jsevilla.memeschilenos.data.network.utils.ConnectionUtils
import com.jsevilla.memeschilenos.data.network.utils.SupportInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val fakeNetworkModule = module {
    single { ConnectionUtilsImplMocked() } bind ConnectionUtils::class
    factory { SupportInterceptor() }
    single { NetworkHandler(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "memes_chilenos_database"
        ).build()
    }
    single(named(name = "SERVICE")) { ApiClient.create(get(), BuildConfig.BaseURL) }
    single<HomePoints> { HomePointsImpl(get(), get(named(name = "SERVICE"))) }
}