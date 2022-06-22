package com.jsevilla.memeschilenos.data.base

import android.app.Application
import com.jsevilla.memeschilenos.data.di.mapperDataModule
import com.jsevilla.memeschilenos.data.di.repositoryModule
import com.jsevilla.memeschilenos.data.mock.SecurePreferencesImplMocked
import com.jsevilla.memeschilenos.data.mock.fakeNetworkModule
import com.jsevilla.memeschilenos.data.preferences.SecurePreferences
import com.jsevilla.memeschilenos.domain.di.useCasesModule
import com.jsevilla.memeschilenos.domain.entity.Failure
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.mock
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

abstract class BaseUseCaseUniTest : KoinTest {
    @Mock
    private lateinit var context: Application

    @Before
    fun before() {
        // to remove 'A Koin Application has already been started' exception at the beginning of the test.
        stopKoin()
        val fakePreferencesModule = module {
            single { SecurePreferencesImplMocked() } bind SecurePreferences::class
        }
        startKoin {
            context = Application()
            androidContext(context)
            modules(
                arrayListOf(
                    fakePreferencesModule,
                    fakeNetworkModule,
                    mapperDataModule,
                    repositoryModule,
                    useCasesModule,
                )
            )
        }
    }


    protected fun <T> printUseCaseSuccessObject(someDataObject: T) {
        println("Use case invocation: Success!")
        println(someDataObject)
    }

    protected fun <T> printUseCaseSuccessList(someList: List<T>) {
        println("Use case invocation: Success!")
        println("List size: ${someList.size}")
        println("List content:")
        someList.forEach { println(it) }
    }

    protected fun printUseCaseFailure(failure: Failure) {
        when (failure) {
            is Failure.UnauthorizedOrForbidden -> throw Exception("Force a log out. server throw 401 - 403")
            is Failure.None -> throw Exception("Ups! Something went REALLY wrong. Contact support.")
            is Failure.NetworkConnectionLostSuddenly -> throw SSLException("The internet connection is suddenly lost.")
            is Failure.NoNetworkDetected -> throw SSLException("No network detected, you will not be able to connect to the Internet")
            is Failure.SSLError -> throw SSLHandshakeException("Verify the SSL.")
            is Failure.TimeOut -> throw SocketTimeoutException("Time out exception. The server took too long to answer.")
            is Failure.DataToDomainMapperFailure -> throw IllegalArgumentException("Error assigning data between layers")
            is Failure.ServerBodyError -> throw Exception("Service Error Response (Error Body)")
            is Failure.ServiceUncaughtFailure -> throw Exception("500 - ServiceUncaughtFailure")
        }
    }
}
