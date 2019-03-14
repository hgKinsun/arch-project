package com.okynk.archproject.feature.screen

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okynk.archproject.R
import com.okynk.archproject.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.rv_profiles
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        rv_profiles.addItemDecoration(
            DividerItemDecoration(
                rv_profiles.context,
                RecyclerView.VERTICAL
            )
        )

        mViewModel.loadingLiveData.observe(this, Observer {
            handlePleaseWaitDialog(it)
        })

        mViewModel.errorLiveData.observe(this, Observer {
            showMessageDialog(it)
        })

        mViewModel.profilesLiveData.observe(this, Observer {
            mAdapter.setNewData(it.data)
        })

        mViewModel.getProfiles()
    }
}