package com.okynk.archproject.feature.screen

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.usecase.UseCase
import com.okynk.archproject.feature.base.BaseViewModel
import com.okynk.archproject.util.LoadMoreStatus
import com.okynk.archproject.util.SingleLiveEvent
import timber.log.Timber

class MainViewModel(private val useCase: UseCase) : BaseViewModel() {

    val newDataEvent = SingleLiveEvent<List<ProfileEntity>>()
    val loadMoreDataEvent = SingleLiveEvent<List<ProfileEntity>>()
    val loadMoreStatusEvent = SingleLiveEvent<LoadMoreStatus>()
    val stopRefreshingEvent = SingleLiveEvent<Void>()

    private var mPage = 0

    override fun start() {
        getProfiles()
    }

    fun getProfiles(fromRefresh: Boolean = false) {
        execute {
            useCase.getProfiles(GetProfilesPostModel())
                .doOnSubscribe {
                    if (!fromRefresh) {
                        pleaseWaitLiveData.postValue(true)
                    }
                }
                .doFinally {
                    if (fromRefresh) {
                        stopRefreshingEvent.call()
                    } else {
                        pleaseWaitLiveData.postValue(false)
                    }
                }
                .subscribe({ response ->
                    mPage = response.page
                    newDataEvent.postValue(response.data)
                }, { error ->
                    Timber.d(error)
                    errorMessageLiveData.postValue(error.localizedMessage)
                })
        }
    }

    fun onClickClearDb() {

    }

    fun onSrlRefresh() {
        getProfiles(fromRefresh = true)
    }

    fun onLoadMore() {
        mPage++
        val postModel = GetProfilesPostModel(page = mPage)

        execute {
            useCase.getProfiles(postModel)
                .subscribe({ response ->
                    loadMoreDataEvent.postValue(response.data)

                    if (mPage == 3) {
                        // example if page 3 is "last page"
                        loadMoreStatusEvent.postValue(LoadMoreStatus.End())
                    } else {
                        loadMoreStatusEvent.postValue(LoadMoreStatus.Complete())
                    }
                }, { error ->
                    Timber.d(error)
                    errorMessageLiveData.postValue(error.localizedMessage)
                    loadMoreStatusEvent.postValue(LoadMoreStatus.Fail())
                })
        }
    }
}