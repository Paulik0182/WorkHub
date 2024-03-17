package com.nayya.workhub.ui.search_offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.ConditionSelectionVacancyInteractor
import com.nayya.workhub.utils.mutable

class SearchOfferViewModel(
    private val conditionSelectionVacancyInteractor: ConditionSelectionVacancyInteractor
) : ViewModel() {

    class Factory(
        private val conditionSelectionVacancyInteractor: ConditionSelectionVacancyInteractor

    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchOfferViewModel(
                conditionSelectionVacancyInteractor
            ) as T
        }
    }

    val distanceFromCityLiveData: LiveData<DistanceInKmEntity> =
        MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        conditionSelectionVacancyInteractor.getDistance {
            distanceFromCityLiveData.mutable().postValue(it)
        }
    }
}