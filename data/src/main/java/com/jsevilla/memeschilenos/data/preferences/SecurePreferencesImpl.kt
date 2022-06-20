package com.jsevilla.memeschilenos.data.preferences

import android.content.SharedPreferences
import com.jsevilla.memeschilenos.data.di.PREF_KEY_INTRO_VALUE

class SecurePreferencesImpl(
    private val prefs: SharedPreferences
) : SecurePreferences {
    override fun getIntroValue(): Boolean = prefs.getBoolean(PREF_KEY_INTRO_VALUE, false)
    override fun setIntroFinish() = prefs.edit().putBoolean(PREF_KEY_INTRO_VALUE, true).apply()
}