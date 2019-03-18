/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.util.generalutil

class GeneralUtilImpl : GeneralUtil {
    override fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis() / 1000
    }
}