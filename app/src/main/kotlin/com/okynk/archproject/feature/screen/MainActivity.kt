package com.okynk.archproject.feature.screen

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okynk.archproject.R
import com.okynk.archproject.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.rv_profiles
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity() {

    private val mViewModel: MainViewModel by viewModel()
    private lateinit var mAdapter: ProfileAdapter

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initComponent() {
        mAdapter = ProfileAdapter()
        mAdapter.openLoadAnimation()

        rv_profiles.adapter = mAdapter
        rv_profiles.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        mViewModel.viewState.observe(this, Observer {
            it?.let { render(it) }
        })

        mViewModel.getProfiles()
    }

    private fun render(viewState: MainViewState) {
        viewState.error?.let {
            Timber.d(it)
        } ?: run {
            viewState.data?.let {
                mAdapter.setNewData(it.data)
            }
        }
    }
}