package com.jsevilla.memeschilenos.feature.ui.activity.legal

import android.os.Bundle
import android.util.Log
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.databinding.ActivityLegalBinding
import com.jsevilla.memeschilenos.feature.base.BaseActivity
import com.jsevilla.memeschilenos.feature.ui.bottomsheet.message.MessageBottomSheet
import com.jsevilla.memeschilenos.utils.recreateActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LegalActivity : BaseActivity<ActivityLegalBinding, LegalViewModel>() {
    private val legalViewModel: LegalViewModel by viewModel()

    override val getLayoutId: Int
        get() = R.layout.activity_legal

    override val getViewModel: LegalViewModel
        get() = legalViewModel

    override val getBindingVariable: Int
        get() = BR.legalViewModel

    override val isDayNight: Boolean
        get() = getViewModel.getDayNight()

    override fun onStartActivity() {}

    override fun onResumeActivity() {}

    override fun onCreateActivity(
        viewDataBinding: ActivityLegalBinding,
        savedInstanceState: Bundle?
    ) {
        viewDataBinding.imgBack.setOnClickListener {
            recreateActivity(activity = this, 3)
        }

        observeViewModel()
    }

    override fun onPauseActivity() {}

    override fun onActivityConnect() {}

    override fun onActivityOffConnect() {}

    private fun observeViewModel() {
        getViewModel.errorCause.observe(this) {
            val message = getStringMessage(it)
            val bottomSheet = MessageBottomSheet(
                title = getString(R.string.txtTitleError),
                subTitle = message,
                textButton = getString(R.string.btnToAccept)
            )
            bottomSheet.isCancelable = false
            bottomSheet.show(supportFragmentManager, "getViewModel.errorCause")
        }
    }
}
