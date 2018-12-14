/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmObject

open class ProfileDbModel(
    var first: String = "",
    var last: String = "",
    var email: String = "",
    var address: String = "",
    var created: String = "",
    var balance: String = ""
) : RealmObject()

