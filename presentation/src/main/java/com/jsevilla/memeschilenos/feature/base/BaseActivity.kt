package com.jsevilla.memeschilenos.feature.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, out V : BaseViewModel> : AppCompatActivity() {
    private lateinit var viewDataBinding: T

    private var _viewModel: V? = null

    abstract val getLayoutId: Int

    abstract val getViewModel: V

    abstract val getBindingVariable: Int

    abstract val isDayNight: Boolean

    override fun onStart() {
        super.onStart()
        onStartActivity()
        if (isDayNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onResume() {
        super.onResume()
        onResumeActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId)
        _viewModel = if (_viewModel == null) getViewModel else _viewModel
        viewDataBinding.setVariable(getBindingVariable, _viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()

        if (savedInstanceState == null) {
            onCreateActivity(viewDataBinding, intent.extras)
        }
    }

    override fun onPause() {
        super.onPause()
        onPauseActivity()
    }

    abstract fun onStartActivity()

    abstract fun onResumeActivity()

    abstract fun onCreateActivity(viewDataBinding: T, savedInstanceState: Bundle?)

    abstract fun onPauseActivity()
}
