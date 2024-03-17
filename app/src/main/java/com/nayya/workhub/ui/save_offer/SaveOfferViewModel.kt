package com.nayya.workhub.ui.save_offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.utils.mutable

class SaveOfferViewModel(
    private val collectionVacanciesInteractor: CollectionVacanciesInteractor
) : ViewModel() {

    class Factory(
        private val collectionVacanciesInteractor: CollectionVacanciesInteractor
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SaveOfferViewModel(collectionVacanciesInteractor) as T
        }
    }

    val favoriteVacanciesLiveData: LiveData<List<VacancyJobEntity>> = MutableLiveData()
    val favoriteSelectedVacancyJobLiveData: LiveData<VacancyJobEntity> = MutableLiveData()

    init {
        collectionVacanciesInteractor.getCollectionVacancies {
            favoriteVacanciesLiveData.mutable().postValue(it)
        }
    }

    fun onVacancyJobClick(favoriteVacancyJobEntity: VacancyJobEntity) {
        favoriteSelectedVacancyJobLiveData.mutable().postValue(favoriteVacancyJobEntity)
    }


//    fun onDeleteVacancy(jobId: String) {
//        collectionVacanciesInteractor.delete(jobId)
//        collectionVacanciesInteractor.getCollectionVacancies {
//            vacanciesLiveData.mutable().postValue(it)
//        }
//    }
}