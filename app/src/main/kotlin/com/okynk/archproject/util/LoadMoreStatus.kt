/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.util

sealed class LoadMoreStatus {
    object Complete : LoadMoreStatus()
    object Fail : LoadMoreStatus()
    object End : LoadMoreStatus()
}


