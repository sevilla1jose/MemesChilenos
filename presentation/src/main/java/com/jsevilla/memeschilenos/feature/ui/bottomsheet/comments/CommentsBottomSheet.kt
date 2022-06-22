package com.jsevilla.memeschilenos.feature.ui.bottomsheet.comments

import android.widget.Toast
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.BotoomSheetCommentsBinding
import com.jsevilla.memeschilenos.feature.base.BaseBottomSheet

class CommentsBottomSheet(
    private val title: String,
    private val textEdit: String,
    private val textButton: String,
    private val callback: (comments: String) -> Unit
) : BaseBottomSheet<BotoomSheetCommentsBinding>() {

    override val getLayoutId: Int
        get() = R.layout.botoom_sheet_comments

    override fun onFragmentViewReady(viewDataBinding: BotoomSheetCommentsBinding) {
        viewDataBinding.txtTitle.text = title
        viewDataBinding.textInputComments.hint = textEdit
        viewDataBinding.btnOk.text = textButton
        viewDataBinding.btnOk.setOnClickListener {
            val data = viewDataBinding.textInputComments.text.toString()
            when {
                data == "" -> {
                    Toast.makeText(context, R.string.txtEmptyCommets, Toast.LENGTH_LONG).show()
                }
                data.length < 8 -> {
                    Toast.makeText(context, R.string.txtLengthComments, Toast.LENGTH_LONG).show()
                }
                else -> {
                    dismiss()
                    callback(data)
                }
            }
        }
    }
}
