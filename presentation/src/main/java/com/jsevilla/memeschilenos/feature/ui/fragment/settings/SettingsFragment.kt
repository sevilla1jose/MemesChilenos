package com.jsevilla.memeschilenos.feature.ui.fragment.settings

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.FragmentSettingsBinding
import com.jsevilla.memeschilenos.feature.base.BaseFragment
import com.jsevilla.memeschilenos.feature.ui.activity.home.HomeActivity
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
