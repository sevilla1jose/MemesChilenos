package com.jsevilla.memeschilenos.data.mock

import com.jsevilla.memeschilenos.data.preferences.SecurePreferences

class SecurePreferencesImplMocked : SecurePreferences {
    override fun getIntroValue(): Boolean {
        return false
    }

    override fun setIntroFinish() {
        println("Register intro")
    }

    override fun getDayNight(): Boolean {
        return true
    }

    override fun setDayNight(isDayOrNight: Boolean) {
        return println("Register DayOrNight $isDayOrNight")
    }
}
