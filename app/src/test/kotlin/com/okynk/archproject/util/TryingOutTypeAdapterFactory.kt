package com.okynk.archproject.util

import com.okynk.archproject.util.SerializerTest.UserEntity
import com.google.gson.JsonElement

class TryingOutTypeAdapterFactory :
    BaseCustomTypeAdapterFactory<UserEntity>(UserEntity::class.java) {

    override
    fun afterRead(deserialized: JsonElement) {
        val custom = deserialized.asJsonObject.get("custom").asJsonObject
        custom.remove("size")
    }
}