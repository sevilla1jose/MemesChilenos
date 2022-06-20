package com.jsevilla.memeschilenos.data.repository

import com.jsevilla.memeschilenos.data.preferences.SecurePreferences
import com.jsevilla.memeschilenos.domain.repository.IntroRepository

class IntroRepositoryImpl(
    private val preferences: SecurePreferences
) : IntroRepository {
    override fun getIntro(): Boolean = preferences.getIntroValue()
    override fun setIntroFinish() = preferences.setIntroFinish()
}
