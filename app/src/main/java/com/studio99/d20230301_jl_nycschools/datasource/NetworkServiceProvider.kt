package com.studio99.d20230301_jl_nycschools.datasource

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkServiceProvider {
    val client: NycSchoolsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NycSchoolsApi::class.java)
    }
}