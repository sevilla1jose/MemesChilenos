package com.jsevilla.memeschilenos.domain.repository

interface IntroRepository {
    fun getIntro(): Boolean
    fun setIntroFinish()
    fun getDayNight(): Boolean
    fun setDayNight(isDayOrNight: Boolean)
}
