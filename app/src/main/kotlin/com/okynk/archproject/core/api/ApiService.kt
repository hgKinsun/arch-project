/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.api

import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val PROFILES_URL = "api/a5bb1rrq"
    }

    @GET(PROFILES_URL)
    fun getProfiles(@Query("results") results: Int, @Query("page") page: Int): Observable<ListWrapperResponse<ProfileResponse>>
}