/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.storage.model.PaginatedListDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel
import com.okynk.archproject.core.storage.realm.RealmStorage
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.Realm
import io.realm.kotlin.where

class LocalDataSource(
    val realmStorage: RealmStorage,
    val listEntityDbMapper: Mapper<PaginatedListEntity<ProfileEntity>, PaginatedListDbModel<ProfileDbModel>>,
    val listDbEntityMapper: Mapper<PaginatedListDbModel<ProfileDbModel>, PaginatedListEntity<ProfileEntity>>
) : DataSource {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return Observable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            val data = realm.where<PaginatedListDbModel<ProfileDbModel>>()
                .equalTo(PaginatedListDbModel.QUERY_PAGE, postModel.page).findFirst()
            data?.let {
                emitter.onNext(listDbEntityMapper.map(it))
            } ?: run {
                emitter.onNext(PaginatedListEntity())
            }
            realm.close()
            emitter.onComplete()
        }
    }

    override fun saveProfiles(postModel: GetProfilesPostModel, data: PaginatedListEntity<ProfileEntity>): Completable {
        return realmStorage.insert(listEntityDbMapper.map(data), LastUpdateDbModel.PROFILE)
    }

}