package com.jsevilla.memeschilenos.feature.ui.activity.intro

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.github.appintro.AppIntroPageTransformerType
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.feature.ui.bottomsheet.message.MessageBottomSheet
import com.jsevilla.memeschilenos.utils.recreateActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroActivity : AppIntro2() {
    private val introViewModel: IntroViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        if (introViewModel.getDayNight()) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        } else {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }
    }

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
                Manifest.permission.WRITE_EXTERNAL_STORAGE
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
        recreateActivity(this, 1)
    }

    override fun onUserDeniedPermission(permissionName: String) {
        val bottomSheet = MessageBottomSheet(
            title = getString(R.string.txtTitleMessagePermission),
            subTitle = getString(R.string.txtSubTitleMessagePermission),
            textButton = getString(R.string.btnToAccept)
        ) {}
        bottomSheet.isCancelable = false
        bottomSheet.show(supportFragmentManager, "getViewModel.errorCause")
    }

    override fun onUserDisabledPermission(permissionName: String) {
        val bottomSheet = MessageBottomSheet(
            title = getString(R.string.txtTitleMessagePermission),
            subTitle = getString(R.string.txtMessagePermission),
            textButton = getString(R.string.btnToAccept)
        ) { onClickActionButton() }
        bottomSheet.isCancelable = false
        bottomSheet.show(supportFragmentManager, "getViewModel.errorCause")
    }

    private fun onClickActionButton() {
        goToNextSlide()
    }
}
