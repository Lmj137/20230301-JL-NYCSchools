package com.studio99.d20230301_jl_nycschools.domain.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SchoolTestResults(
    override val dbn: String,

    @Json(name = "school_name")
    override val schoolName: String,

    @Json(name = "sat_critical_reading_avg_score")
    val satReadingAvg: String? = null,

    @Json(name = "sat_math_avg_score")
    val satMathAvg: String? = null,

    @Json(name = "sat_writing_avg_score")
    val satWritingAvg: String? = null,

) : School