/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.general

import com.google.gson.Gson
import com.okynk.archproject.core.api.ApiService
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.storage.model.CacheDbModel
import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel
import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.core.util.fromJson
import io.reactivex.Completable
import io.reactivex.Observable

class GeneralLocalDataSource(
    private val mRealmStorage: RealmStorage,
    private val mGson: Gson,
    private val mProfileEntityDbMapper: Mapper<ProfileEntity, ProfileDbModel>,
    private val mProfileDbEntityMapper: Mapper<ProfileDbModel, ProfileEntity>
) : GeneralDataSource {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return mRealmStorage.isLastUpdateExpired(
            LastUpdateDbModel.PROFILE_LIST,
            postModel.toString()
        ).flatMap { expired ->
            if (expired) {
                Observable.empty<PaginatedListEntity<ProfileEntity>>()
            } else {
                mRealmStorage.getFirst<CacheDbModel>(CacheDbModel::class.java).map {
                    mGson.fromJson<PaginatedListEntity<ProfileEntity>>(it.data)
                }
            }
        }
    }

    override fun saveProfiles(
        postModel: GetProfilesPostModel,
        data: PaginatedListEntity<ProfileEntity>
    ): Completable {
        val cache = CacheDbModel(
            ApiService.PROFILES_URL,
            postModel.toString(),
            mGson.toJson(data)
        )

        return mRealmStorage.insert(cache).andThen(
            mRealmStorage.touchLastUpdate(
                LastUpdateDbModel.PROFILE_LIST,
                postModel.toString()
            )
        )
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return mRealmStorage.isLastUpdateExpired(LastUpdateDbModel.PROFILE).flatMap { expired ->
            if (expired) {
                Observable.empty<ProfileEntity>()
            } else {
                mRealmStorage.getFirst<ProfileDbModel>(ProfileDbModel::class.java).map {
                    mProfileDbEntityMapper.map(it)
                }
            }
        }
    }

    override fun saveProfile(data: ProfileEntity): Completable {
        return mRealmStorage.insert(mProfileEntityDbMapper.map(data))
            .andThen(mRealmStorage.touchLastUpdate(LastUpdateDbModel.PROFILE))
    }
}