/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.general

import com.okynk.archproject.BuildConfig
import com.okynk.archproject.core.api.ApiService
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.util.CoreConstants
import io.reactivex.Completable
import io.reactivex.Observable

class GeneralRemoteDataSource(
    private val mApiService: ApiService,
    private val mMapperList: Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>>,
    private val mMapperItem: Mapper<ProfileResponse, ProfileEntity>
) : GeneralDataSource {

    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return mApiService.getProfiles(BuildConfig.API_KEY, postModel.results, postModel.page)
            .map { t ->
                mMapperList.map(t)
        }
    }

    override fun saveProfiles(
        postModel: GetProfilesPostModel,
        data: PaginatedListEntity<ProfileEntity>
    ): Completable {
        throw Exception(CoreConstants.EXCEPTION_NOT_IMPLEMENTED_REMOTE_DATASOURCE)
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return mApiService.getProfiles(BuildConfig.API_KEY, 1, 1).map {
            mMapperItem.map(it.results[CoreConstants.FIRST_INDEX])
        }
    }

    override fun saveProfile(data: ProfileEntity): Completable {
        throw Exception(CoreConstants.EXCEPTION_NOT_IMPLEMENTED_REMOTE_DATASOURCE)
    }
}