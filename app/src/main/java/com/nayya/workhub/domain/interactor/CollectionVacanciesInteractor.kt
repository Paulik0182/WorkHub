package com.nayya.workhub.domain.interactor

import com.nayya.workhub.domain.entity.VacancyEntity

interface CollectionVacanciesInteractor {

    fun getCollectionVacancies(callback: (List<VacancyEntity>) -> Unit)
}