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
import com.okynk.archproject.core.util.None
import com.okynk.archproject.core.util.Optional
import com.okynk.archproject.core.util.asOptional
import com.okynk.archproject.util.Constants
import io.reactivex.Completable
import io.reactivex.Observable

class LocalDataSource(
    val realmStorage: RealmStorage,
    val profileEntityDbMapper: Mapper<ProfileEntity, ProfileDbModel>,
    val profileDbEntityMapper: Mapper<ProfileDbModel, ProfileEntity>
) : DataSource {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<Optional<PaginatedListEntity<ProfileEntity>>> {
        throw Exception(Constants.EXCEPTION_NOT_IMPLEMENTED_LOCAL_DATASOURCE)
    }

    override fun getProfile(): Observable<Optional<ProfileEntity>> {
        return realmStorage.isLastUpdateExpired(LastUpdateDbModel.PROFILE).flatMap { expired ->
            if (expired) {
                Observable.just(None)
            } else {
                realmStorage.getFirst<ProfileDbModel>(ProfileDbModel::class.java).map {
                    profileDbEntityMapper.map(it).asOptional()
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