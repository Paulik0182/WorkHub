package com.nayya.workhub.utils

import com.google.gson.GsonBuilder
import com.nayya.workhub.data.CategorySelectionRepoImpl
import com.nayya.workhub.data.CategoryVacanciesRepoImpl
import com.nayya.workhub.data.VacanciesTypeRepoImpl
import com.nayya.workhub.data.retrofit.ApiService
import com.nayya.workhub.domain.repo.CategorySelectionRepo
import com.nayya.workhub.domain.repo.CategoryVacanciesRepo
import com.nayya.workhub.domain.repo.VacanciesTypeRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.hh.ru/"
private const val API_KEY = "1api"

class MyDiy {

    val vacanciesTypeRepo: VacanciesTypeRepo by lazy {
        VacanciesTypeRepoImpl()
    }

    private val interceptor = HttpLoggingInterceptor().apply {
        this.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val categoryVacanciesRepo: CategoryVacanciesRepo by lazy {
        CategoryVacanciesRepoImpl()
    }
    val categorySelectionRepo: CategorySelectionRepo by lazy {
        CategorySelectionRepoImpl()
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    val imdbApi: ApiService by lazy { retrofit.create(ApiService::class.java) }

}