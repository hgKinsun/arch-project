package com.okynk.archproject.core.usecase.general

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import io.reactivex.Observable

interface GeneralUseCase {
    fun getProfiles(postModel: GetProfilesPostModel = GetProfilesPostModel()): Observable<PaginatedListEntity<ProfileEntity>>
    fun getProfile(): Observable<ProfileEntity>
}