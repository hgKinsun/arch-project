/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.api.model.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("first") val first: String = "",
    @SerializedName("last") val last: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("address") val address: String = "",
    @SerializedName("created") val created: String = "",
    @SerializedName("balance") val balance: String = ""
)