package com.jsevilla.memeschilenos.feature.ui.fragment.home

import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentHomeBinding
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel >(
    R.layout.fragment_home
) {
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModel()

    override val getViewModel: HomeFragmentViewModel
        get() = homeFragmentViewModel

    override val getBindingVariable: Int
        get() = BR.homeFragmentViewModel

    override fun onResumeFragment() {}

    override fun onViewCreatedFragment(viewDataBinding: FragmentHomeBinding) {}

    override fun onPauseFragment() {}

    override fun onStopFragment() {}
}
