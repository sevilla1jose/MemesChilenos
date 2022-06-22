package com.jsevilla.memeschilenos.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jsevilla.memeschilenos.data.preferences.SecurePreferences
import com.jsevilla.memeschilenos.data.preferences.SecurePreferencesImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferencesModule = module {
    single(named("securePrefs")) { provideSecurePreferences(androidApplication()) }
    single<SecurePreferences> { SecurePreferencesImpl(get(named("securePrefs"))) }
}

private const val SECURE_PREFS_FILE_KEY = "com.jsevilla.memeschilenos.data.secure_preferences"

const val PREF_KEY_INTRO_VALUE = "key_intro_value"
const val PREF_KEY_DAY_OR_NIGHT_VALUE = "key_day_or_night_value"

private fun provideSecurePreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(SECURE_PREFS_FILE_KEY, Context.MODE_PRIVATE)