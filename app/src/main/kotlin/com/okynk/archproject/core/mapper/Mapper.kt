/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

interface Mapper<in FROM, TO> {
    fun map(from: FROM): TO

    companion object {
        const val PROFILE_RESPONSE_TO_ENTITY = "ProfileResponse to ProfileEntity"
        const val PROFILE_LIST_RESPONSE_TO_ENTITY =
            "ListWrapperResponse<ProfileResponse> to PaginatedListEntity<ProfileEntity>"
        const val PROFILE_ENTITY_TO_DB = "ProfileEntity to ProfileDbModel"
        const val PROFILE_LIST_ENTITY_TO_DB =
            "PaginatedListEntity<ProfileEntity> to PaginatedListDbModel<ProfileDbModel>"
        const val PROFILE_DB_TO_ENTITY = "ProfileDbModel to ProfileEntity"
        const val PROFILE_LIST_DB_TO_ENTITY =
            "PaginatedListDbModel<ProfileDbModel> to PaginatedListEntity<ProfileEntity>"
    }
}