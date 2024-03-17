package com.nayya.workhub.data.retrofit

import com.nayya.workhub.domain.entity.VacancyQueryResponse
import com.nayya.workhub.domain.entity.VacancyTypeEntity
import com.nayya.workhub.domain.repo.VacanciesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VacanciesRepoImpl(
    private val apiService: ApiService
) : VacanciesRepo {

    override fun getVacancies(vacancy: List<String>, callback: (List<VacancyTypeEntity>) -> Unit) {
        val call = apiService.queryVacancies(vacancy)
        call.enqueue(object : Callback<VacancyQueryResponse> {
            override fun onResponse(
                call: Call<VacancyQueryResponse>,
                response: Response<VacancyQueryResponse>
            ) {
                if (response.isSuccessful) {
                    callback(response.body()?.workEntities ?: emptyList())
                } else {
                    // Обработка ошибки
                    callback(emptyList())
                }
            }

            override fun onFailure(call: Call<VacancyQueryResponse>, t: Throwable) {
                // Обработка ошибки
                callback(emptyList())
            }
        })
    }
}