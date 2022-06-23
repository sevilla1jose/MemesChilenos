package com.jsevilla.memeschilenos.feature.ui.fragment.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentHomeBinding
import com.jsevilla.memeschilenos.feature.adapter.RvAdapterListMemes
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import com.jsevilla.memeschilenos.feature.ui.bottomsheet.message.MessageBottomSheet
import com.jsevilla.memeschilenos.utils.getStringMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
    R.layout.fragment_home
) {
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModel()
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

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
        fragmentHomeBinding = viewDataBinding
        viewDataBinding.recyclerViewMemes.adapter = adapter
        viewDataBinding.recyclerViewMemes.layoutManager = LinearLayoutManager(requireContext())

        viewDataBinding.swipeRefreshLayoutMemes.setOnRefreshListener {
            getViewModel.getRefreshListMemes()
        }

        observeViewModel()
        getViewModel.getListMemes()
    }

    override fun onPauseFragment() {}

    override fun onStopFragment() {}

    private fun observeViewModel() {
        getViewModel.isListMemes.observe(this) { memes ->
            adapter.update(memes.children)
            fragmentHomeBinding.swipeRefreshLayoutMemes.isRefreshing = false
        }

        getViewModel.errorCause.observe(this) {
            val message = getStringMessage(it)
            val bottomSheet = MessageBottomSheet(
                title = getString(R.string.txtTitleError),
                subTitle = message,
                textButton = getString(R.string.btnToAccept)
            ) {}
            bottomSheet.isCancelable = false
            bottomSheet.show(childFragmentManager, "getViewModel.errorCause")
        }
    }
}
