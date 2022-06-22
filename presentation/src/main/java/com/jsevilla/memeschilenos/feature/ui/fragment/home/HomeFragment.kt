package com.jsevilla.memeschilenos.feature.ui.fragment.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentHomeBinding
import com.jsevilla.memeschilenos.feature.adapter.RvAdapterListMemes
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
    R.layout.fragment_home
) {
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModel()

    private val adapter by lazy {
        RvAdapterListMemes(
            arrayListOf(),
            requireContext()
        )
    }

    override val getViewModel: HomeFragmentViewModel
        get() = homeFragmentViewModel

    override val getBindingVariable: Int
        get() = BR.homeFragmentViewModel

    override fun onResumeFragment() {}

    override fun onViewCreatedFragment(viewDataBinding: FragmentHomeBinding) {
        viewDataBinding.recyclerViewMemes.adapter = adapter
        viewDataBinding.recyclerViewMemes.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel()
        getViewModel.getListMemes()
    }

    override fun onPauseFragment() {}

    override fun onStopFragment() {}

    private fun observeViewModel() {
        getViewModel.isListMemes.observe(this) { memes ->
            adapter.update(memes.children)
        }
    }
}
