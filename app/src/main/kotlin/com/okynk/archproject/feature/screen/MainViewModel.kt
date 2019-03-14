package com.okynk.archproject.feature.screen

import androidx.lifecycle.MutableLiveData
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.usecase.UseCase
import com.okynk.archproject.feature.base.BaseViewModel
import timber.log.Timber

class MainViewModel(private val useCase: UseCase) : BaseViewModel() {

    val profilesLiveData = MutableLiveData<PaginatedListEntity<ProfileEntity>>()

    fun getProfiles() {
        execute {
            useCase.getProfiles(GetProfilesPostModel())
                .doOnSubscribe {
                    loadingLiveData.postValue(true)
                }
                .doFinally {
                    loadingLiveData.postValue(false)
                }
                .subscribe({ response ->
                    profilesLiveData.postValue(response)
                }, { error ->
                    Timber.d(error)
                    errorLiveData.postValue(error.localizedMessage)
                })
        }
    }
}