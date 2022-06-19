package com.jsevilla.memeschilenos.feature.ui.bottomsheet.message

import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.BottomSheetMessageBinding
import com.jsevilla.memeschilenos.feature.base.BaseBottomSheet

class MessageBottomSheet(
    private val title: String,
    private val subTitle: String,
    private val textButton: String,
    val callClick: () -> Unit
) : BaseBottomSheet<BottomSheetMessageBinding>() {

    override val getLayoutId: Int
        get() = R.layout.bottom_sheet_message

    override fun onFragmentViewReady(viewDataBinding: BottomSheetMessageBinding) {
        viewDataBinding.txtTitle.text = title
        viewDataBinding.txtSubTitle.text = subTitle
        viewDataBinding.btnOk.text = textButton
        viewDataBinding.btnOk.setOnClickListener {
            callClick()
            dismiss()
        }
    }
}