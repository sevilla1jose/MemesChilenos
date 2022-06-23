package com.jsevilla.memeschilenos.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.jsevilla.memeschilenos.R
import com.jsevilla.memeschilenos.feature.ui.activity.home.HomeActivity
import com.jsevilla.memeschilenos.feature.ui.activity.intro.IntroActivity
import com.jsevilla.memeschilenos.feature.ui.activity.legal.LegalActivity

fun AppCompatActivity.recreateActivity(activity: AppCompatActivity, layout: Int) {
    val intent = Intent(activity, HomeActivity::class.java)
    intent.putExtra("layout", layout)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}

fun AppCompatActivity.recreateActivity(activity: AppCompatActivity) {
    val intent = Intent(activity, IntroActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}

fun Fragment.goToTermsAndCond(activity: FragmentActivity) {
    val intent = Intent(activity, LegalActivity::class.java)
    startActivity(intent)
    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}

fun Fragment.recreateActivity(activity: FragmentActivity) {
    val intent = Intent(activity, HomeActivity::class.java)
    intent.putExtra("layout", 3)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}

fun Activity.getStringMessage(exception: Int): String {
    when (exception) {
        1 -> {
            return getString(R.string.error_unauthorized_or_forbidden)
        }
        2 -> {
            return getString(R.string.error_none)
        }
        3 -> {
            return getString(R.string.error_network_connection_lost_suddenly)
        }
        4 -> {
            return getString(R.string.error_not_network_detected)
        }
        5 -> {
            return getString(R.string.error_ssl_error)
        }
        6 -> {
            return getString(R.string.error_time_out)
        }
        else -> {
            return getString(R.string.error_none)
        }
    }
}

fun Fragment.getStringMessage(exception: Int): String {
    when (exception) {
        1 -> {
            return getString(R.string.error_unauthorized_or_forbidden)
        }
        2 -> {
            return getString(R.string.error_none)
        }
        3 -> {
            return getString(R.string.error_network_connection_lost_suddenly)
        }
        4 -> {
            return getString(R.string.error_not_network_detected)
        }
        5 -> {
            return getString(R.string.error_ssl_error)
        }
        6 -> {
            return getString(R.string.error_time_out)
        }
        else -> {
            return getString(R.string.error_none)
        }
    }
}
