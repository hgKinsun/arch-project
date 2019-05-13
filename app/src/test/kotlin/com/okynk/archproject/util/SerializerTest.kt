package com.okynk.archproject.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.TreeTypeAdapter.newFactoryWithMatchRawType
import com.google.gson.reflect.TypeToken
import com.okynk.archproject.core.util.fromJson
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class SerializerTest {

    data class UserEntity(
        @SerializedName("id")
        val userId: Int,

        @SerializedName("cover_url")
        val coverUrl: String,

        @SerializedName("full_name")
        val fullName: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("followers")
        val followers: Int,

        @SerializedName("email")
        val email: String,

        @SerializedName("id_list")
        val idList: List<String>
    )

    private val JSON_RESPONSE = "{\n \"id\": 1,\n " +
            "\"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n " +
            "\"full_name\": \"Simon Hill\",\n " +
            "\"description\": \"Curabitur gravida nisi at nibh. In hac habitasse " +
            "platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer " +
            "eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. " +
            "Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo " +
            "placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, " +
            "lacinia eget, tincidunt eget, tempus vel, pede.\",\n " +
            "\"followers\": 7484,\n " +
            "\"email\": \"jcooper@babbleset.edu\"\n}"

    private val JSON_RESPONSE_WITH_ID_LIST = "{\n" +
            "  \"id_list\": [\n" +
            "    1,\n" +
            "    2,\n" +
            "    3\n" +
            "  ],\n" +
            "  \"id\": 923144,\n" +
            "  \"cover_url\": \"https://hahahahaha.com\",\n" +
            "  \"full_name\": \"tks tks\",\n" +
            "  \"description\": \"Hello there testing text\",\n" +
            "  \"followers\": 9999,\n" +
            "  \"email\": \"tks@test.com\"\n" +
            "}"

    private val JSON_RESPONSE_WRONG_TYPE_ID_LIST = "{\n" +
            "  \"id_list\": 1\n" +
            "}"

    lateinit var mockContext: JsonDeserializationContext
    lateinit var userEntityTypeToken: TypeToken<UserEntity>
    lateinit var customDeserializer: JsonDeserializer<UserEntity>

    @Before
    fun setUp() {
        userEntityTypeToken = TypeToken.get(UserEntity::class.java)
        mockContext = mock(JsonDeserializationContext::class.java)
        customDeserializer = CustomDeserializer()
    }

    @Test
    fun shouldSerialize() {
        val userEntityOne = Gson().fromJson<UserEntity>(JSON_RESPONSE)
        val jsonString = Gson().toJson(userEntityOne)
        val userEntityTwo = Gson().fromJson<UserEntity>(jsonString)

        assertEquals(userEntityOne.userId, userEntityTwo.userId)
        assertEquals(userEntityOne.fullName, userEntityTwo.fullName)
        assertEquals(userEntityOne.followers, userEntityTwo.followers)
    }

    @Test
    fun shouldDesearialize() {
        val userEntity = Gson().fromJson<UserEntity>(JSON_RESPONSE)

        assertEquals(userEntity.userId, 1)
        assertEquals(userEntity.fullName, "Simon Hill")
        assertEquals(userEntity.followers, 7484)
    }

    @Test
    @Throws(IOException::class)
    fun testCustomDeserializeIndirectlyViaAutomaticTypeAdapterBinding() {
        val gson =
            GsonBuilder().registerTypeAdapter(UserEntity::class.java, customDeserializer).create()
        val userEntity = gson.fromJson<UserEntity>(JSON_RESPONSE)

        assertEquals(userEntity.userId, 1)
        assertEquals(userEntity.fullName, "Simon Hill")
        assertEquals(userEntity.followers, 7484)
    }

    @Test
    @Throws(IOException::class)
    fun testCustomDeserializerIndirectlyViaManualTypeAdapterBinding() {
        val gson = Gson()
        val typeAdapterFactory = newFactoryWithMatchRawType(userEntityTypeToken, customDeserializer)
        val userEntityCustomAdapter = typeAdapterFactory.create<UserEntity>(gson, userEntityTypeToken)
        val userEntity1 = userEntityCustomAdapter.fromJson(JSON_RESPONSE_WITH_ID_LIST)


        val getDelegate = gson.getDelegateAdapter(typeAdapterFactory, userEntityTypeToken)
        val getAdapter = gson.getAdapter(UserEntity::class.java)

        assertEquals(userEntity1.userId, 923144)
        assertEquals(userEntity1.fullName, "tks tks")
        assertEquals(userEntity1.followers, 9999)
        assertEquals(userEntity1.idList, listOf("1","2","3"))
    }

    @Test(expected = JsonSyntaxException::class)
    @Throws(IOException::class)
    fun testJsonSyntaxExceptionExpectArray() {
        Gson().fromJson<UserEntity>(JSON_RESPONSE_WRONG_TYPE_ID_LIST)
    }

    @Test
    fun testCustomDeserializerWithConvertStringToArrayList() {
        val gson = Gson()
        val typeAdapterFactory = newFactoryWithMatchRawType(userEntityTypeToken, customDeserializer)
        val userEntityCustomAdapter =
            typeAdapterFactory.create<UserEntity>(gson, userEntityTypeToken)
        val userEntity = userEntityCustomAdapter.fromJson(JSON_RESPONSE_WRONG_TYPE_ID_LIST)

        assertEquals(userEntity.idList, emptyList<String>())
    }

    @Test
    fun testEmptyArrayAsNullTypeAdapterFactory() {
        val gson = Gson()
    }

}