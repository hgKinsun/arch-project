/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.api.model.response

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("results") val results: String = "",
    @SerializedName("page") val page: String = ""
)