package com.nayya.workhub.domain.interactor

import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity

interface CollectionVacanciesInteractor {

    fun getCollectionVacancies(callback: (List<VacancyJobEntity>) -> Unit)
    fun getVacancyJob(id: String, callback: (VacancyJobEntity?) -> Unit)
}