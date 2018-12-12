/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.api.model.response

import com.google.gson.annotations.SerializedName

data class ListWrapperResponse<T>(
    @SerializedName("results") val results: List<T>,
    @SerializedName("info") val info: InfoResponse
)