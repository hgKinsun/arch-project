/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.util

sealed class LoadMoreStatus {
    class Complete : LoadMoreStatus()
    class Fail : LoadMoreStatus()
    class End : LoadMoreStatus()
}


