package com.jsevilla.memeschilenos.feature.ui.fragment.search

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentSearchBinding
import com.jsevilla.memeschilenos.feature.adapter.RvAdapterListMemes
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import com.jsevilla.memeschilenos.feature.ui.bottomsheet.message.MessageBottomSheet
import com.jsevilla.memeschilenos.utils.getStringMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(
    R.layout.fragment_search
) {
    private val searchFragmentViewModel: SearchFragmentViewModel by viewModel()

    private val adapter by lazy {
        RvAdapterListMemes(
            arrayListOf(),
            requireContext()
        )
    }

    override val getViewModel: SearchFragmentViewModel
        get() = searchFragmentViewModel

    override val getBindingVariable: Int
        get() = BR.searchFragmentViewModel

    override fun onResumeFragment() {}

    override fun onViewCreatedFragment(viewDataBinding: FragmentSearchBinding) {
        viewDataBinding.textInputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let {
                    if (it.isNotEmpty()) {
                        getViewModel.getListSearchMemes(it.toString())
                    }
                }
            }
        })

        viewDataBinding.recyclerViewMemes.adapter = adapter
        viewDataBinding.recyclerViewMemes.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel()
        getViewModel.getListMemes()
    }

    override fun onPauseFragment() {}

    override fun onStopFragment() {}

    private fun observeViewModel() {
        getViewModel.isListMemes.observe(this) { memes ->
            adapter.update(memes.children.asReversed())
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
