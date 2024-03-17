package com.nayya.workhub.domain.interactor

import com.nayya.workhub.domain.entity.favorite.FavoriteVacancyJobEntity

interface FavoriteCollectionVacanciesJobInteractor {

    fun getFavoriteCollectionVacanciesJob(callback: (List<FavoriteVacancyJobEntity>) -> Unit)
    fun getVacancyJob(id: String, callback: (FavoriteVacancyJobEntity?) -> Unit)
}