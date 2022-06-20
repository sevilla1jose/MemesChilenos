package com.jsevilla.memeschilenos.feature.ui.activity.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnResume
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.databinding.ActivityMainBinding
import com.jsevilla.memeschilenos.feature.base.BaseActivity
import com.jsevilla.memeschilenos.feature.ui.activity.home.HomeActivity
import com.jsevilla.memeschilenos.feature.ui.activity.intro.IntroActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModel()

    override val getLayoutId: Int
        get() = R.layout.activity_main

    override val getViewModel: MainViewModel
        get() = mainViewModel

    override val getBindingVariable: Int
        get() = BR.mainViewModel

    override fun onStartActivity() {
        observeViewModel()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 10000L
                slideUp.doOnResume {
                    getViewModel.initProcess()
                }
                slideUp.doOnEnd {
                    splashScreenView.remove()
                }
                slideUp.start()
            }
        }
    }

    override fun onResumeActivity() {}

    override fun onCreateActivity(
        viewDataBinding: ActivityMainBinding,
        savedInstanceState: Bundle?
    ) {
        getViewModel.initProcess()
    }

    override fun onPauseActivity() {}

    private fun observeViewModel() {
        getViewModel.initPage.observe(this) {
            if (it) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            } else {
                val intent = Intent(this@MainActivity, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }
}
