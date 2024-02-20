package com.nayya.workhub.ui.job_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.favorite.FavoriteVacancyJobEntity
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.interactor.FavoriteCollectionVacanciesJobInteractor
import com.nayya.workhub.domain.repo.OfferFavoriteRepo
import com.nayya.workhub.utils.mutable

class JobDetailsViewModel(
    private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
    private val vacancyJobId: String,
    private val favoriteCollectionVacanciesJobInteractor: FavoriteCollectionVacanciesJobInteractor,
    private val offerFavoriteRepo: OfferFavoriteRepo
) : ViewModel() {


    fun onFavoriteChange(vacancyJobEntity: VacancyJobEntity) {
        collectionVacanciesInteractor.setFavorite(
            vacancyJobEntity.key,
            !vacancyJobEntity.isFavorite
        )
    }

    class Factory(
        private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
        private val vacancyJobId: String,
        private val favoriteCollectionVacanciesJobInteractor: FavoriteCollectionVacanciesJobInteractor,
        private val offerFavoriteRepo: OfferFavoriteRepo
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return JobDetailsViewModel(
                collectionVacanciesInteractor,
                vacancyJobId,
                favoriteCollectionVacanciesJobInteractor,
                offerFavoriteRepo
            ) as T
        }
    }

    val vacancyJobLiveData: LiveData<VacancyJobEntity> = MutableLiveData()
    val favoriteVacancyJobLiveData: LiveData<FavoriteVacancyJobEntity> = MutableLiveData()

    init {
        if (vacancyJobLiveData.value == null) {
            collectionVacanciesInteractor.getVacancyJob(vacancyJobId) {
                it.let {
                    vacancyJobLiveData.mutable().postValue(it)
                }
            }
        }

//        if (favoriteVacancyJobLiveData.value == null) {
//            favoriteCollectionVacanciesJobInteractor.getVacancyJob(vacancyJobId) {
//                it.let {
//                    favoriteVacancyJobLiveData.mutable().postValue(it)
//                }
//            }
//        }
    }
}