/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.mockdata

import com.okynk.archproject.core.api.model.response.InfoResponse
import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.storage.model.ProfileDbModel

val mockProfileDbModel = ProfileDbModel(
    "Will",
    "Carroll",
    "tanrabbit55@gmail.com",
    "62352 Mills Way",
    "June 1, 2012",
    "\$8,108.39"
)

val mockProfileEntity = ProfileEntity(
    "Will",
    "Carroll",
    "tanrabbit55@gmail.com",
    "62352 Mills Way",
    "June 1, 2012",
    "\$8,108.39"
)

val mockProfileEntity2 = ProfileEntity(
    "Delphine",
    "Hickle",
    "Delphine.Hickle@raoul.org",
    "497 Amy Ridge",
    "December 1, 2010",
    "\$6,020.61"
)

val mockProfileEntity3 = ProfileEntity(
    "Valentina",
    "Treutel",
    "Valentina.Treutel@brendan.biz",
    "80912 Laurel Ramp",
    "August 5, 2012",
    "\$9,383.46"
)

val mockListProfileEntity = listOf(
    mockProfileEntity,
    mockProfileEntity2,
    mockProfileEntity3
)

val mockProfileResponse = ProfileResponse(
    "Will",
    "Carroll",
    "tanrabbit55@gmail.com",
    "62352 Mills Way",
    "June 1, 2012",
    "\$8,108.39"
)

val mockProfileResponse2 = ProfileResponse(
    "Delphine",
    "Hickle",
    "Delphine.Hickle@raoul.org",
    "497 Amy Ridge",
    "December 1, 2010",
    "\$6,020.61"
)

val mockProfileResponse3 = ProfileResponse(
    "Valentina",
    "Treutel",
    "Valentina.Treutel@brendan.biz",
    "80912 Laurel Ramp",
    "August 5, 2012",
    "\$9,383.46"
)

val mockListProfileResponse = listOf<ProfileResponse>(
    mockProfileResponse,
    mockProfileResponse2,
    mockProfileResponse3
)

val mockInfoResponse = InfoResponse(
    "10",
    "1"
)

val mockListWrapperProfileResponse = ListWrapperResponse<ProfileResponse>(
    mockListProfileResponse,
    mockInfoResponse
)

val mockPaginatedListProfileEntity = PaginatedListEntity<ProfileEntity>(
    mockListProfileEntity,
    10,
    1
)