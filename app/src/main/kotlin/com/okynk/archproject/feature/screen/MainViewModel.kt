package com.okynk.archproject.feature.screen

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.usecase.UseCase
import com.okynk.archproject.feature.base.BaseViewModel
import timber.log.Timber

class MainViewModel(private val useCase: UseCase) : BaseViewModel<MainViewState>() {

    init {
        viewState.value = MainViewState()
    }

    fun getProfiles() {
        execute {
            useCase.getProfiles(GetProfilesPostModel())
                .subscribe({ result ->
                    Timber.d(result.toString())
                }, { error ->
                    Timber.d(error)
                })
        }
    }
}