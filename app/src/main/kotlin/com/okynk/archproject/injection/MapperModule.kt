package com.okynk.archproject.injection

import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.mapper.ProfileDbModelEntityMapper
import com.okynk.archproject.core.mapper.ProfileEntityDbModelMapper
import com.okynk.archproject.core.mapper.ProfileListResponseEntityMapper
import com.okynk.archproject.core.mapper.ProfileResponseEntityMapper
import com.okynk.archproject.core.storage.model.ProfileDbModel
import org.koin.dsl.module.module

val mapperModule = module {

    single<Mapper<ProfileResponse, ProfileEntity>>(
        name = Mapper.PROFILE_RESPONSE_TO_ENTITY,
        definition = { ProfileResponseEntityMapper() }
    )

    single<Mapper<ProfileEntity, ProfileDbModel>>(
        name = Mapper.PROFILE_ENTITY_TO_DB,
        definition = { ProfileEntityDbModelMapper() }
    )

    single<Mapper<ProfileDbModel, ProfileEntity>>(
        name = Mapper.PROFILE_DB_TO_ENTITY,
        definition = { ProfileDbModelEntityMapper() }
    )

    single<Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>>>(
        name = Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY,
        definition = { ProfileListResponseEntityMapper(get(Mapper.PROFILE_RESPONSE_TO_ENTITY)) }
    )
}