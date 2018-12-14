/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmObject

open class LastUpdateDbModel(
    var key: String = "",
    var timestamp: Long = 0
) : RealmObject()