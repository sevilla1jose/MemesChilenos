package com.jsevilla.memeschilenos.feature.ui.bottomsheet.message

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.databinding.FragmentMessageBinding
import com.jsevilla.memeschilenos.feature.base.BaseBottomSheet

class MessageFragment(
    private val title: String,
    private val textButton: String
) : BaseBottomSheet<FragmentMessageBinding, MessageViewModel>(MessageViewModel::class) {

    private var txtTitle: AppCompatTextView? = null
    private var btnOk: AppCompatButton? = null

    override val getLayoutId: Int
        get() = R.layout.fragment_message

    override val getBindingVariable: Int
        get() = BR.messageViewModel

    override fun onFragmentViewReady(view: View) {
        txtTitle = view.findViewById(R.id.txtTitle)
        btnOk = view.findViewById(R.id.btnOk)

        txtTitle?.text = title
        btnOk?.text = textButton
        btnOk?.setOnClickListener {
            dismiss()
        }
    }
}