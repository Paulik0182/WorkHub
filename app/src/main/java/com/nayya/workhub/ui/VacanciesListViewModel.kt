package com.nayya.workhub.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.VacancyEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.utils.mutable

class VacanciesListViewModel(
    private val collectionVacanciesInteractor: CollectionVacanciesInteractor
) : ViewModel() {

    class Factory(private val collectionWorksRepo: CollectionVacanciesInteractor) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VacanciesListViewModel(collectionWorksRepo) as T
        }
    }

    val vacanciesLiveData: LiveData<List<VacancyEntity>> = MutableLiveData()

    init {
        collectionVacanciesInteractor.getCollectionVacancies {
            vacanciesLiveData.mutable().postValue(it)
        }
    }
}