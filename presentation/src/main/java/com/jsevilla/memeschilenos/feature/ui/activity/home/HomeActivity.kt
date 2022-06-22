package com.jsevilla.memeschilenos.feature.ui.activity.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jsevilla.memeschilenos.BR
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.databinding.ActivityHomeBinding
import com.jsevilla.memeschilenos.feature.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    private val homeViewModel: HomeViewModel by viewModel()

    override val getLayoutId: Int
        get() = R.layout.activity_home

    override val getViewModel: HomeViewModel
        get() = homeViewModel

    override val getBindingVariable: Int
        get() = BR.homeViewModel

    override val isDayNight: Boolean
        get() = getViewModel.getDayNight()

    override fun onStartActivity() {}

    override fun onResumeActivity() {}

    override fun onCreateActivity(
        viewDataBinding: ActivityHomeBinding,
        savedInstanceState: Bundle?
    ) {
        val navView: BottomNavigationView = viewDataBinding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        savedInstanceState?.let { data ->
            navView.selectedItemId = selectedItemNavigation(data.getInt("layout"))
        }
    }

    override fun onPauseActivity() {}

    private fun selectedItemNavigation(item: Int): Int {
        return when (item) {
            1 -> R.id.navigation_home
            2 -> R.id.navigation_search
            3 -> R.id.navigation_settings
            else -> R.id.navigation_home
        }
    }
}
