package com.studio99.d20230301_jl_nycschools.models

import com.studio99.d20230301_jl_nycschools.domain.dto.School

data class SchoolOverview(
    override val dbn: String,
    override val schoolName: String,
    val overviewParagraph: String,
    val location: String,
    val phoneNumber: String,
) : School {
    /**
     * While this does break the immutable aspect of this model class, this action is strictly
     * for the UI, and completely immutable view state is an MVI feature..
     */
    var clickAction: () -> Unit = {}
}
