/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.repository.general

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.datasource.general.GeneralDataSource
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import io.reactivex.Observable

class GeneralRepositoryImpl(
    private val mLocalDataSource: GeneralDataSource,
    private val mRemoteDataSource: GeneralDataSource
) : GeneralRepository {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return mLocalDataSource.getProfiles(postModel)
            .switchIfEmpty(mRemoteDataSource.getProfiles(postModel).flatMap {
                mLocalDataSource.saveProfiles(postModel, it).andThen(Observable.just(it))
            })
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return mLocalDataSource.getProfile().switchIfEmpty(
            mRemoteDataSource.getProfile().doOnNext {
                mLocalDataSource.saveProfile(it)
            }
        )
    }
}