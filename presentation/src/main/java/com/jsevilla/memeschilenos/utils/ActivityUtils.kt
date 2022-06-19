package com.jsevilla.memeschilenos.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavControllerFromFragmentContainer(id: Int): NavController {
    val fragment = supportFragmentManager.findFragmentById(id)
    check(fragment is NavHostFragment) { ("Activity $this does not have a NavHostFragment") }
    return fragment.navController
}

fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}
