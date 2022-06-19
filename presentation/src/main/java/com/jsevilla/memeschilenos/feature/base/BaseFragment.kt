package com.jsevilla.memeschilenos.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<T : ViewDataBinding, out V : BaseViewModel>(
    getLayoutId: Int
) : Fragment(getLayoutId) {

    private lateinit var viewDataBinding: T

    private var _viewModel: V? = null

    abstract val getViewModel: V

    abstract val getBindingVariable: Int

    override fun onResume() {
        super.onResume()
        onResumeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = if (_viewModel == null) getViewModel else _viewModel
        if (_viewModel == null) {
            throw Exception("View Model must not be null.")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding = DataBindingUtil.bind(view)!!
        viewDataBinding.setVariable(getBindingVariable, _viewModel)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewDataBinding.executePendingBindings()
        if (savedInstanceState == null)
            onViewCreatedFragment(viewDataBinding)
    }

    override fun onPause() {
        super.onPause()
        onPauseFragment()
    }

    override fun onStop() {
        super.onStop()
        onStopFragment()
    }

    abstract fun onResumeFragment()

    abstract fun onViewCreatedFragment(viewDataBinding: T)

    abstract fun onPauseFragment()

    abstract fun onStopFragment()
}