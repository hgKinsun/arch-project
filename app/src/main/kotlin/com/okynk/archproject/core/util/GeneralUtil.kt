/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.util

fun parseInt(str: String?, defaultValue: Int = 0): Int {
    return str?.toIntOrNull() ?: defaultValue
}

fun getCurrentTimestamp(): Long {
    return System.currentTimeMillis() / 1000
}
