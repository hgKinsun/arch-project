package com.okynk.archproject.core.repository

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.datasource.DataSource
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import io.reactivex.Observable

class RepositoryImpl(
    val localDataSource: DataSource,
    val remoteDataSource: DataSource
) : Repository {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return remoteDataSource.getProfiles(postModel)
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return localDataSource.getProfile().switchIfEmpty {
            remoteDataSource.getProfile().doOnNext {
                localDataSource.saveProfile(it)
            }
        }
    }
}