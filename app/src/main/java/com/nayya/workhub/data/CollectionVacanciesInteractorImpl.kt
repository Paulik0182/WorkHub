package com.nayya.workhub.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import com.nayya.workhub.domain.entity.VacancyEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.repo.VacanciesRepo
import com.nayya.workhub.domain.repo.VacanciesTypeRepo

class CollectionVacanciesInteractorImpl(
    private val vacanciesTypeRepo: VacanciesTypeRepo,
    private val vacanciesRepo: VacanciesRepo,
    private val context: Context
) : CollectionVacanciesInteractor {

    override fun getCollectionVacancies(callback: (List<VacancyEntity>) -> Unit) {

        if (isConnected()) {
            val works = vacanciesTypeRepo.getVacanciesType()

            Handler(Looper.getMainLooper()).postDelayed({
                vacanciesRepo.getVacancies(works) { vacanciesTypes ->
                    val vacancies = mutableListOf<VacancyEntity>()

                    vacanciesTypes.forEach { vacancyType ->
                        vacancies.addAll(vacancyType.vacanciesList)
                    }
                    callback(vacancies)
                }
            }, 1_000)
        } else {
            val fakeData = getFakeData()
            callback(fakeData)
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager
            .getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }

    private fun getFakeData(): List<VacancyEntity> {
        return listOf(
            VacancyEntity("1", "IT"),
            VacancyEntity("2", "Android"),
            VacancyEntity("3", "Java"),
            VacancyEntity("4", "IT"),
            VacancyEntity("5", "IT"),
            VacancyEntity("6", "Android"),
            VacancyEntity("7", "Java")
        )
    }
}