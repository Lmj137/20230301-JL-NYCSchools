package com.studio99.d20230301_jl_nycschools.domain.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SchoolEntry(
    override val dbn: String,

    @Json(name = "school_name")
    override val schoolName: String,

    @Json(name = "overview_paragraph")
    val overviewParagraph: String? = null,

    val location: String? = null,

    @Json(name = "phone_number")
    val phoneNumber: String? = null,

    @Json(name = "total_students")
    val totalStudents: Int? = null,

    @Json(name = "graduation_rate")
    val graduationRate: Float? = null,

) : School