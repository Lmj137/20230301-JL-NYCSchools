package com.studio99.d20230301_jl_nycschools.presenters

import androidx.lifecycle.ViewModel
import com.studio99.d20230301_jl_nycschools.datasource.SchoolRepository

class SchoolListViewModel : ViewModel() {

    private val repository = SchoolRepository()

    // use a backing property for overviewFlow
    var overviewFlow = repository.schools
    var currentSchool = "#0000"

    fun getSchoolDetails(dbn: String) = repository.getSchoolDetails(dbn)
}