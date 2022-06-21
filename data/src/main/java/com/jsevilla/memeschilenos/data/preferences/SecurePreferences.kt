package com.jsevilla.memeschilenos.data.preferences

interface SecurePreferences {
    fun getIntroValue(): Boolean
    fun setIntroFinish()
    fun getDayNight(): Boolean
    fun setDayNight(isDayOrNight: Boolean)
}