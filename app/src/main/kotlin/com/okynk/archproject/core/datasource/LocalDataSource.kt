/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel
import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.util.Constants
import io.reactivex.Completable
import io.reactivex.Observable

class LocalDataSource(
    val realmStorage: RealmStorage,
    val profileEntityDbMapper: Mapper<ProfileEntity, ProfileDbModel>,
    val profileDbEntityMapper: Mapper<ProfileDbModel, ProfileEntity>
) : DataSource {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        throw Exception(Constants.EXCEPTION_NOT_IMPLEMENTED_LOCAL_DATASOURCE)
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return realmStorage.isLastUpdateExpired(LastUpdateDbModel.PROFILE).flatMap { expired ->
            if (expired) {
                Observable.empty()
            } else {
                realmStorage.getFirst<ProfileDbModel>(ProfileDbModel::class.java).map {
                    profileDbEntityMapper.map(it)
                }
            }
        }
    }

    override fun saveProfile(data: ProfileEntity): Completable {
        return realmStorage.insert(profileEntityDbMapper.map(data)).andThen {
            realmStorage.touchLastUpdate(LastUpdateDbModel.PROFILE)
        }
    }
}