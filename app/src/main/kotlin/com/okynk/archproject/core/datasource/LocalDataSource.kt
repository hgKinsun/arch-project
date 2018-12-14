/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.util.Constants
import io.reactivex.Completable
import io.reactivex.Observable

class LocalDataSource : DataSource {
    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        throw Exception(Constants.EXCEPTION_NOT_IMPLEMENTED_LOCAL_DATASOURCE)
    }

    override fun saveProfiles(postModel: GetProfilesPostModel, data: PaginatedListEntity<ProfileEntity>): Completable {
        throw Exception(Constants.EXCEPTION_NOT_IMPLEMENTED_LOCAL_DATASOURCE)
    }

}