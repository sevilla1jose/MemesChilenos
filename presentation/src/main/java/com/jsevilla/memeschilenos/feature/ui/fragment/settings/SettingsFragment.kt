package com.jsevilla.memeschilenos.feature.ui.fragment.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.BuildConfig
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentSettingsBinding
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import com.jsevilla.memeschilenos.feature.ui.activity.home.HomeActivity
import com.jsevilla.memeschilenos.feature.ui.bottomsheet.comments.CommentsBottomSheet
import com.jsevilla.memeschilenos.feature.ui.bottomsheet.message.MessageBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsFragmentViewModel>(
    R.layout.fragment_settings
) {
    private val settingsFragmentViewModel: SettingsFragmentViewModel by viewModel()

    override val getViewModel: SettingsFragmentViewModel
        get() = settingsFragmentViewModel

    override val getBindingVariable: Int
        get() = BR.settingsFragmentViewModel

    override fun onResumeFragment() {}

    override fun onViewCreatedFragment(viewDataBinding: FragmentSettingsBinding) {
        viewDataBinding.switchDarkOrLight.isChecked = getViewModel.getDayNight()
        viewDataBinding.switchDarkOrLight.setOnCheckedChangeListener { _, isChecked ->
            getViewModel.setDayNight(isChecked)
            setActivityDayOrNight()
        }
        viewDataBinding.layoutComments.setOnClickListener {
            val bottomSheet = CommentsBottomSheet(
                title = getString(R.string.txtComments),
                textEdit = getString(R.string.txtYourComments),
                textButton = getString(R.string.btnToAccept),
                callback = onClickButtonComments()
            )
            bottomSheet.show(childFragmentManager, "viewDataBinding.CommentsBottomSheet")
        }
        viewDataBinding.txtVersionApp.text = getString(R.string.appVersion, BuildConfig.VERSION_NAME)
    }

    private fun onClickButtonComments(): (comments: String) -> Unit {
        return { _ ->
            val bottomSheet = MessageBottomSheet(
                title = getString(R.string.txtThank),
                subTitle = getString(R.string.txtDetailsThank),
                textButton = getString(R.string.btnToAccept)
            )
            bottomSheet.show(childFragmentManager, "viewDataBinding.MessageBottomSheet")
        }
    }

    override fun onPauseFragment() {}

    override fun onStopFragment() {}

    private fun setActivityDayOrNight() {
        if (getViewModel.getDayNight()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        recreateActivity()
    }

    private fun recreateActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        intent.putExtra("layout", 3)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
