package com.nayya.workhub.data.retrofit

import com.nayya.workhub.domain.entity.VacancyQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("works")
    fun queryVacancies(
        @Query("vacancy") vacancy: List<String>
    ): Call<VacancyQueryResponse>
}