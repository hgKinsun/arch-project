/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.api.model.post

data class GetProfilesPostModel(
    var results: Int = 10,
    var page: Int = 1
) {
    override fun toString(): String {
        return "GetProfilesPostModel(results=$results, page=$page)"
    }
}