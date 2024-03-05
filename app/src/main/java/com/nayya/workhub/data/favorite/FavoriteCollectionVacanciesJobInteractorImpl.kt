package com.nayya.workhub.data.favorite

import com.nayya.workhub.domain.entity.favorite.FavoriteVacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.interactor.FavoriteCollectionVacanciesJobInteractor
import com.nayya.workhub.domain.repo.OfferFavoriteRepo

class FavoriteCollectionVacanciesJobInteractorImpl(
    private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
    private val offerFavoriteRepo: OfferFavoriteRepo
) : FavoriteCollectionVacanciesJobInteractor {

    override fun getFavoriteCollectionVacanciesJob(callback: (List<FavoriteVacancyJobEntity>) -> Unit) {
        collectionVacanciesInteractor.getCollectionVacancies { vacancies ->
            val favoriteVacancies = vacancies.map {
                val isFavorite = offerFavoriteRepo.isFavorite(it.key)
                it.mapToFavoriteVacancy(isFavorite = isFavorite)
            }
            callback(favoriteVacancies)
        }
    }

    override fun getVacancyJob(id: String, callback: (FavoriteVacancyJobEntity?) -> Unit) {
        collectionVacanciesInteractor.getVacancyJob(id) {
            val isFavorite = offerFavoriteRepo.isFavorite(id)
            val favoriteVacancy = it?.mapToFavoriteVacancy(
                isFavorite = isFavorite
            )
            callback.invoke(favoriteVacancy)
        }
    }
}