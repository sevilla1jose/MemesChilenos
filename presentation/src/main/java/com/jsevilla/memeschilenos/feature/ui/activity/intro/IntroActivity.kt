package com.jsevilla.memeschilenos.feature.ui.activity.intro

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.github.appintro.AppIntroPageTransformerType
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.feature.ui.activity.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroActivity : AppIntro2() {
    private val introViewModel: IntroViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(newInstance(R.layout.intro_custom_layout1))
        addSlide(newInstance(R.layout.intro_custom_layout2))
        addSlide(newInstance(R.layout.intro_custom_layout3))
        addSlide(newInstance(R.layout.intro_custom_layout4))

        isIndicatorEnabled = true
        isSystemBackButtonLocked = false
        isButtonsEnabled = true
        isSkipButtonEnabled = false

        setTransformer(AppIntroPageTransformerType.SlideOver)
        showStatusBar(show = true)

        askForPermissions(
            permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            slideNumber = 2,
            required = true
        )

        askForPermissions(
            permissions = arrayOf(
                Manifest.permission.CAMERA
            ),
            slideNumber = 3,
            required = true
        )
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        introViewModel.setIntroFinish()
        val intent = Intent(this@IntroActivity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onUserDeniedPermission(permissionName: String) {
        // User pressed "Deny" on the permission dialog
    }

    override fun onUserDisabledPermission(permissionName: String) {
        // User pressed "Deny" + "Don't ask again" on the permission dialog
    }
}