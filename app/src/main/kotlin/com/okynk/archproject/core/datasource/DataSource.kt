/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.util.Optional
import io.reactivex.Completable
import io.reactivex.Observable

interface DataSource {
    fun getProfiles(postModel: GetProfilesPostModel = GetProfilesPostModel()): Observable<Optional<PaginatedListEntity<ProfileEntity>>>
    fun getProfile(): Observable<Optional<ProfileEntity>>
    fun saveProfile(data: ProfileEntity): Completable
}