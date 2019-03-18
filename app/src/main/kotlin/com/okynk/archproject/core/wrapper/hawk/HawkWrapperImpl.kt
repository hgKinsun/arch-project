/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.wrapper.hawk

import android.content.Context
import com.orhanobut.hawk.Hawk

class HawkWrapperImpl(private val mContext: Context) : HawkWrapper {

    override fun initialize() {
        Hawk.init(mContext).build()
    }

    override fun contains(key: String?): Boolean {
        return Hawk.contains(key)
    }

    override fun count(): Long {
        return Hawk.count()
    }

    override fun <T : Any?> put(key: String?, value: T): Boolean {
        return Hawk.put(key, value)
    }

    override fun isBuilt(): Boolean {
        return Hawk.isBuilt()
    }

    override fun destroy() {
        Hawk.destroy()
    }

    override fun <T : Any?> get(key: String?): T {
        return Hawk.get(key)
    }

    override fun <T : Any?> get(key: String?, defaultValue: T): T {
        return Hawk.get(key, defaultValue)
    }

    override fun deleteAll(): Boolean {
        return Hawk.deleteAll()
    }

    override fun delete(key: String?): Boolean {
        return Hawk.delete(key)
    }

}