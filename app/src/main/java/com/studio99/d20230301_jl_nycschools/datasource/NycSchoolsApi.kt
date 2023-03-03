package com.studio99.d20230301_jl_nycschools.datasource

import com.studio99.d20230301_jl_nycschools.domain.dto.SchoolEntry
import com.studio99.d20230301_jl_nycschools.domain.dto.SchoolTestResults
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://data.cityofnewyork.us/resource/"

interface NycSchoolsApi {
    @GET("s3k6-pzi2.json")
    suspend fun requestSchools(): List<SchoolEntry>

    @GET("f9bf-2cp4.json")
    suspend fun requestSchoolDetails(@Query("dbn") dbn: String): List<SchoolTestResults>
}