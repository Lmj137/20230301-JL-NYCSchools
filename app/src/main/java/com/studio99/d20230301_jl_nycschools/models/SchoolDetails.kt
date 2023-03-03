package com.studio99.d20230301_jl_nycschools.models

import com.studio99.d20230301_jl_nycschools.domain.dto.School

data class SchoolDetails(
    override val dbn: String,
    override val schoolName: String,
    val satReadingAvg: String,
    val satMathAvg: String,
    val satWritingAvg: String,
) : School
