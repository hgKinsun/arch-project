package com.okynk.archproject.feature.screen

import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okynk.archproject.R
import com.okynk.archproject.feature.base.BaseActivity
import com.okynk.archproject.util.Constants
import com.okynk.archproject.util.LoadMoreStatus
import kotlinx.android.synthetic.main.activity_main.rv_profiles
import kotlinx.android.synthetic.main.activity_main.srl
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var mAdapter: ProfileAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.menu_cleardb -> viewModel.onClickClearDb()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initComponent() {
        initRecyclerView()

        srl.setOnRefreshListener {
            viewModel.onSrlRefresh()
        }

        initObservers()

        viewModel.start()
    }

    private fun initRecyclerView() {
        mAdapter = ProfileAdapter()
        mAdapter.openLoadAnimation()
        mAdapter.setEnableLoadMore(true)
        mAdapter.setPreLoadNumber(Constants.PRELOAD_NUMBER)
        mAdapter.setOnLoadMoreListener({
            viewModel.onLoadMore()
        }, rv_profiles)

        rv_profiles.adapter = mAdapter
        rv_profiles.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_profiles.addItemDecoration(
            DividerItemDecoration(
                rv_profiles.context,
                RecyclerView.VERTICAL
            )
        )
    }

    private fun initObservers() {
        viewModel.newDataEvent.observe(this, Observer {
            mAdapter.setNewData(it)
        })

        viewModel.loadMoreDataEvent.observe(this, Observer {
            mAdapter.addData(it)
        })

        viewModel.stopRefreshingEvent.observe(this, Observer {
            srl.isRefreshing = false
        })

        viewModel.loadMoreStatusEvent.observe(this, Observer {
            when (it) {
                is LoadMoreStatus.Complete -> mAdapter.loadMoreComplete()
                is LoadMoreStatus.Fail -> mAdapter.loadMoreFail()
                is LoadMoreStatus.End -> mAdapter.loadMoreEnd()
            }
        })
    }
}