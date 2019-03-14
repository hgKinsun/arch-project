/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmObject

open class CacheDbModel(
    var apiUrl: String = "",
    var postModel: String = "",
    var data: String = ""
) : RealmObject()