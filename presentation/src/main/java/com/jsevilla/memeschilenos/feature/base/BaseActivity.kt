package com.jsevilla.memeschilenos.feature.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, out V : BaseViewModel> : AppCompatActivity() {

    private lateinit var viewDataBinding: T

    private var _viewModel: V? = null

    abstract val getLayoutId: Int

    abstract val getViewModel: V

    abstract val getBindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId)
        _viewModel = if (_viewModel == null) getViewModel else _viewModel
        viewDataBinding.setVariable(getBindingVariable, _viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()

        if (savedInstanceState == null)
            onCreateActivity(viewDataBinding)
    }

    abstract fun onCreateActivity(viewDataBinding: T)
}