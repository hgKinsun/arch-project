package com.okynk.archproject.util

import android.location.Location
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.okynk.archproject.util.SerializerTest.UserEntity

class EmptyArrayAsNullTypeAdapterFactory : TypeAdapterFactory {

    companion object {

        // Add classes here as needed
        private val classesAllowedEmptyArrayAsNull = arrayOf(
            Location::class.java,
            UserEntity::class.java)

        private fun isAllowedClass(rawType: Class<*>): Boolean {
            return classesAllowedEmptyArrayAsNull.find { rawType.isAssignableFrom(it) } != null
        }
    }

    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        val delegate = gson.getDelegateAdapter(this, type)

        val rawType = type.rawType as Class<T>

        return object : TypeAdapter<T>() {

            override fun write(out: JsonWriter, value: T?) {
                delegate.write(out, value)
            }

            override fun read(reader: JsonReader): T? {
                return if (reader.peek() === JsonToken.BEGIN_ARRAY && isAllowedClass(rawType)) {
                    reader.beginArray()

                    // If the array is empty, assume it is signifying null
                    if (!reader.hasNext()) {
                        reader.endArray()
                        null
                    } else {
                        throw JsonParseException("Not expecting a non-empty array when deserializing: ${type.rawType.name}")
                    }
                } else {
                    delegate.read(reader)
                }
            }
        }
    }
}