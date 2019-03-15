package com.okynk.archproject.feature.base

import android.os.Bundle
import android.text.TextUtils
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.okynk.archproject.R
import kotlinx.android.synthetic.main.activity_base.toolbar

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: T

    private lateinit var mPleaseWaitDialog: MaterialDialog
    private lateinit var mMessageDialog: MaterialDialog
    private var mInBackground = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectViewModel()

        val containerMaster =
            layoutInflater.inflate(R.layout.activity_base, null) as CoordinatorLayout
        val containerContent = containerMaster.findViewById<FrameLayout>(R.id.container_content)
        layoutInflater.inflate(getLayoutResId(), containerContent, true)
        super.setContentView(containerMaster)
        setSupportActionBar(toolbar)

        mPleaseWaitDialog = MaterialDialog(this).message(R.string.general_label_pleasewait)
            .cancelable(false)
            .cancelOnTouchOutside(false)

        mMessageDialog = MaterialDialog(this).message(R.string.general_label_error)
            .positiveButton(R.string.general_button_ok)

        initObservers()
        initComponent()
    }

    override fun onResume() {
        super.onResume()
        mInBackground = false
    }

    override fun onPause() {
        mInBackground = true
        super.onPause()
    }

    /**
     * This is the actual content layout
     */
    @LayoutRes abstract fun getLayoutResId(): Int

    /**
     * Will be called after onCreate finished
     */
    abstract fun initComponent()

    /**
     * Child class must implement this to inject the actual ViewModel
     */
    abstract fun injectViewModel()

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
        if (!mInBackground) {
            mPleaseWaitDialog.show()
        }
    }

    fun dismissPleaseWaitDialog() {
        if (mPleaseWaitDialog.isShowing) {
            mPleaseWaitDialog.dismiss()
        }
    }

    fun handlePleaseWaitDialog(show: Boolean) {
        if (show) {
            showPleaseWaitDialog()
        } else {
            dismissPleaseWaitDialog()
        }
    }

    fun showMessageDialog(message: String) {
        mMessageDialog.message(text = message)
        if (!mInBackground) {
            mMessageDialog.show()
        }
    }

    fun showMessageDialog(@StringRes messageResId: Int) {
        mMessageDialog.message(messageResId)
        if (!mInBackground) {
            mMessageDialog.show()
        }
    }

    fun dismissMessageDialog() {
        if (mMessageDialog.isShowing) {
            mMessageDialog.dismiss()
        }
    }

    private fun getLastBackStackTag(): String {
        val manager = supportFragmentManager
        val count = manager.backStackEntryCount
        return if (count > 0) manager.getBackStackEntryAt(count - 1).name!! else ""
    }

    private fun initObservers() {
        viewModel.pleaseWaitDialogEvent.observe(this, Observer {
            handlePleaseWaitDialog(it)
        })

        viewModel.messageDialogEvent.observe(this, Observer {
            showMessageDialog(it)
        })
    }
}