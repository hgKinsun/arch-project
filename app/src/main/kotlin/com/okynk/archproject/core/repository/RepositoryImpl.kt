package com.okynk.archproject.core.repository

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.datasource.DataSource
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import io.reactivex.Observable

class RepositoryImpl(
    private val mLocalDataSource: DataSource,
    private val mRemoteDataSource: DataSource
) : Repository {

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