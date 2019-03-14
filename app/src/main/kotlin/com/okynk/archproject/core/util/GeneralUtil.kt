/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun parseInt(str: String?, defaultValue: Int = 0): Int {
    return str?.toIntOrNull() ?: defaultValue
}

fun getCurrentTimestamp(): Long {
    return System.currentTimeMillis() / 1000
}

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)