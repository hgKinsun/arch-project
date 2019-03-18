package com.okynk.archproject.feature.screen

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.rx.SingleLiveEvent
import com.okynk.archproject.core.usecase.database.DatabaseUseCase
import com.okynk.archproject.core.usecase.general.GeneralUseCase
import com.okynk.archproject.feature.base.BaseViewModel
import com.okynk.archproject.util.LoadMoreStatus
import timber.log.Timber

class MainViewModel(
    private val generalUseCase: GeneralUseCase,
    private val databaseUseCase: DatabaseUseCase
) :
    BaseViewModel() {

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
            generalUseCase.getProfiles(GetProfilesPostModel())
                .doOnSubscribe {
                    if (!fromRefresh) {
                        pleaseWaitDialogEvent.postValue(true)
                    }
                }
                .doFinally {
                    if (fromRefresh) {
                        stopRefreshingEvent.call()
                    } else {
                        pleaseWaitDialogEvent.postValue(false)
                    }
                }
                .subscribe({ response ->
                    mPage = response.page
                    newDataEvent.postValue(response.data)
                }, { error ->
                    Timber.d(error)
                    messageDialogEvent.postValue(error.localizedMessage)
                })
        }
    }

    fun onClickClearDb() {
        execute {
            databaseUseCase.clear()
                .doOnSubscribe {
                    pleaseWaitDialogEvent.postValue(true)
                }
                .doFinally {
                    pleaseWaitDialogEvent.postValue(false)
                }
                .subscribe({
                    messageDialogEvent.postValue("Database Cleared!\n Please refresh the list")
                }, { error ->
                    Timber.d(error)
                    messageDialogEvent.postValue(error.localizedMessage)
                })
        }
    }

    fun onSrlRefresh() {
        getProfiles(fromRefresh = true)
    }

    fun onLoadMore() {
        mPage++
        val postModel = GetProfilesPostModel(page = mPage)

        execute {
            generalUseCase.getProfiles(postModel)
                .subscribe({ response ->
                    loadMoreDataEvent.postValue(response.data)

                    if (mPage == 3) {
                        // example if page 3 is "last page"
                        loadMoreStatusEvent.postValue(LoadMoreStatus.End)
                    } else {
                        loadMoreStatusEvent.postValue(LoadMoreStatus.Complete)
                    }
                }, { error ->
                    Timber.d(error)
                    messageDialogEvent.postValue(error.localizedMessage)
                    loadMoreStatusEvent.postValue(LoadMoreStatus.Fail)
                })
        }
    }
}