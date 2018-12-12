/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource

import io.reactivex.Observable

interface DataSource {
    fun getProfiles(): Observable<>
}