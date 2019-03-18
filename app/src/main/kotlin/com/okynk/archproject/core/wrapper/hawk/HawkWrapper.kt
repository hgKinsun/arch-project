/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.wrapper.hawk

import com.orhanobut.hawk.HawkFacade

/**
 * This is a wrapper for Hawk, so we can unit test it
 */

interface HawkWrapper : HawkFacade {
    fun initialize()
}