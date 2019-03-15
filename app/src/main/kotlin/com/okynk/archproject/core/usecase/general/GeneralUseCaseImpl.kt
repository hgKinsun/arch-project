/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.usecase.general

import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.repository.general.GeneralRepository
import com.okynk.archproject.core.usecase.base.BaseUseCase
import io.reactivex.Observable

class GeneralUseCaseImpl(private val mGeneralRepository: GeneralRepository) : BaseUseCase(),
    GeneralUseCase {
    override fun getProfiles(postModel: GetProfilesPostModel): Observable<PaginatedListEntity<ProfileEntity>> {
        return composeObservable { mGeneralRepository.getProfiles(postModel) }
    }

    override fun getProfile(): Observable<ProfileEntity> {
        return composeObservable { mGeneralRepository.getProfile() }
    }
}