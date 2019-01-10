/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.util

class Constants {
    companion object {
        const val EXCEPTION_NOT_IMPLEMENTED_REMOTE_DATASOURCE = "EXCEPTION_NOT_IMPLEMENTED_REMOTE_DATASOURCE"
        const val EXCEPTION_NOT_IMPLEMENTED_LOCAL_DATASOURCE = "EXCEPTION_NOT_IMPLEMENTED_LOCAL_DATASOURCE"
        const val EXCEPTION_NOT_DEFINED_LASTUPDATELIFETIME = "EXCEPTION_NOT_DEFINED_LASTUPDATELIFETIME"
        const val DATASOURCE_LOCAL = "DATASOURCE_LOCAL"
        const val DATASOURCE_REMOTE = "DATASOURCE_REMOTE"

        const val ONE_HOUR_IN_SECOND: Long = 60 * 60
        const val ONE_DAY_IN_SECOND: Long = 24 * ONE_HOUR_IN_SECOND

        const val FIRST_INDEX = 0
    }
}