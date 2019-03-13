package com.okynk.archproject.feature.base

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.okynk.archproject.R

abstract class BaseActivity : AppCompatActivity() {
    lateinit var pleaseWaitDialog: MaterialDialog
    var inBackground = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pleaseWaitDialog = MaterialDialog(this).message(R.string.general_label_pleasewait)
            .cancelable(false)
            .cancelOnTouchOutside(false)
    }

    override fun onResume() {
        super.onResume()
        inBackground = false
    }

    override fun onPause() {
        inBackground = true
        super.onPause()
    }

    fun setFragment(container: Int, fragment: Fragment, tag: String, addToBackStack: Boolean) {
        if (!TextUtils.equals(tag, getLastBackStackTag())) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(container, fragment, tag)
            if (addToBackStack) fragmentTransaction.addToBackStack(tag)

            fragmentTransaction.commit()
        }
    }

    fun findFragmentByTag(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    fun showPleaseWaitDialog() {
        if (!inBackground) {
            pleaseWaitDialog.show()
        }
    }

    fun dismissPleaseWaitDialog() {
        if (pleaseWaitDialog.isShowing) {
            pleaseWaitDialog.dismiss()
        }
    }

    private fun getLastBackStackTag(): String {
        val manager = supportFragmentManager
        val count = manager.backStackEntryCount
        return if (count > 0) manager.getBackStackEntryAt(count - 1).name!! else ""
    }
}