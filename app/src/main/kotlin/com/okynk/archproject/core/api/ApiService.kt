/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.api

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    companion object {
        private const val SAMPLE_URL = "api/a5bb1rrq"
    }

    @GET(SAMPLE_URL)
    fun getRandomData(): Observable<OBjec>
}