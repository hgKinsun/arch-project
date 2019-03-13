/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.feature.base

data class BaseViewState<T>(
    val data: T? = null,
    val error: Throwable? = null,
    val loading: Boolean = false
)