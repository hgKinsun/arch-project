package com.okynk.archproject.util

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.okynk.archproject.util.SerializerTest.UserEntity
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

class MyClassTypeAdapterFactory :
    BaseCustomTypeAdapterFactory<UserEntity>(UserEntity::class.java) {

    override
    fun afterRead(deserialized: JsonElement) {
        val custom = deserialized.asJsonObject.get("custom").asJsonObject
        custom.remove("size")
    }
}