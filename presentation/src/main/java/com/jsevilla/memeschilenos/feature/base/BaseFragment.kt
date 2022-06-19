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

abstract class BaseFragment<T : ViewDataBinding, out ViewModelType : BaseViewModel>(clazz: KClass<ViewModelType>) :
    Fragment() {

    private lateinit var viewDataBinding: T
    private lateinit var rootView: View
    //val myViewModel: ViewModelType by viewModel(clazz)

    abstract val getLayoutId: Int

    abstract val getBindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if (myViewModel == null) {
            throw Exception("View Model must not be null.")
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        rootView = viewDataBinding.root
        //viewDataBinding.setVariable(getBindingVariable, myViewModel)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewDataBinding.executePendingBindings()
        if (savedInstanceState == null)
            onFragmentViewReady(view)

        onErrorView()
    }

    private fun onErrorView() {
        /*myViewModel.errorCause.observe(viewLifecycleOwner, {
            val message = it as String
            val b = MessageFragment(
                message,
                getString(R.string.btnOk)
            )
            b.show(childFragmentManager, "MessageFragment")

            actionError()
        })*/
    }

    abstract fun onFragmentViewReady(view: View)

    abstract fun actionError()
}