package com.studio99.d20230301_jl_nycschools.datasource

import com.studio99.d20230301_jl_nycschools.domain.dto.SchoolEntry
import com.studio99.d20230301_jl_nycschools.domain.dto.SchoolTestResults
import com.studio99.d20230301_jl_nycschools.models.SchoolDetails
import com.studio99.d20230301_jl_nycschools.models.SchoolOverview
import kotlinx.coroutines.flow.*

/**
 * I've collected this repository and any classes that are involved in data retrieval (network)
 * into the .datasource package for simplicity.
 */
class SchoolRepository {
    private val client = NetworkServiceProvider.client

    val schools = flow {
        emit(
            client.requestSchools()
                .map { it.toOverview() }
        )
    }

    fun getSchoolDetails(dbn: String) = flow {
        val list = client.requestSchoolDetails(dbn)
        if (list.isNotEmpty()) {
            emit(list)
        } else {
            emit(listOf(
                SchoolTestResults(
                    "",
                    "No valid data.",
                    "n/a",
                    "n/a",
                    "n/a"
                )
            ))
        }
    }.mapNotNull { it[0].toDetails() }

        /**
         * The null alternative strings should be organized somewhere.
         */
        private fun SchoolEntry.toOverview() =
            SchoolOverview(
                dbn = dbn,
                schoolName = schoolName,
                overviewParagraph = overviewParagraph ?: "No description.",
                location = location ?: "N/A",
                phoneNumber = phoneNumber ?: "N/A",
            )

        private fun SchoolTestResults.toDetails() =
            SchoolDetails(
                dbn = dbn,
                schoolName = schoolName,
                satReadingAvg = satReadingAvg ?: "0",
                satMathAvg = satMathAvg ?: "0",
                satWritingAvg = satWritingAvg ?: "0",
            )
}