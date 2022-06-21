package com.jsevilla.memeschilenos.feature.ui.fragment.search

import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentSearchBinding
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(
    R.layout.fragment_search
) {
    private val searchFragmentViewModel: SearchFragmentViewModel by viewModel()

    override val getViewModel: SearchFragmentViewModel
        get() = searchFragmentViewModel

    override val getBindingVariable: Int
        get() = BR.searchFragmentViewModel

    override fun onResumeFragment() {}

    override fun onViewCreatedFragment(viewDataBinding: FragmentSearchBinding) {}

    override fun onPauseFragment() {}

    override fun onStopFragment() {}
}
