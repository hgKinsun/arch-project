package com.okynk.archproject.util

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.okynk.archproject.util.SerializerTest.UserEntity
import java.lang.reflect.Type

class CustomDeserializer : JsonDeserializer<UserEntity> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): UserEntity? {
        try {
            val jsonObject = json.asJsonObject
            println(jsonObject.toString())
            if (jsonObject.has("id_list") && jsonObject.get("id_list").isJsonArray) {
                return Gson().fromJson(json, typeOfT)
            } else {

                jsonObject.add("id_list", null)
                jsonObject.addProperty("id_list", "asdad")
                jsonObject.addProperty("id_list", 1)
                jsonObject.addProperty("id_list", true)
                jsonObject.add("id_list", JsonArray())

                println(jsonObject.toString())

                return context.deserialize(jsonObject, typeOfT)
            }
        } catch (e: IllegalStateException) {
            return null
        }
    }
}