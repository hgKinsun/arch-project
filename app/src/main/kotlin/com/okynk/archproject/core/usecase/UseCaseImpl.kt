package com.okynk.archproject.core.usecase

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.repository.Repository
import io.reactivex.Observable

class UseCaseImpl(val repository: Repository) : UseCase {
    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return repository.getProfiles(postModel)
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return repository.getProfile()
    }
}