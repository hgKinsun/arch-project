package com.okynk.archproject.feature.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.afollestad.materialdialogs.MaterialDialog
import com.okynk.archproject.App
import com.okynk.archproject.R
import com.okynk.archproject.core.di.component.ActivityComponent
import com.okynk.archproject.core.di.component.ApplicationComponent
import com.okynk.archproject.core.di.component.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity() {
    lateinit var activityComponent : ActivityComponent
    lateinit var pleaseWaitDialog: MaterialDialog
    var inBackground = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .build()

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

    fun getApplicationComponent(): ApplicationComponent {
        return (application as App).applicationComponent
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