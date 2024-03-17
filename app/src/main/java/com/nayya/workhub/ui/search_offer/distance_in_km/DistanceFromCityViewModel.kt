package com.nayya.workhub.ui.search_offer.distance_in_km

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.ConditionSelectionVacancyInteractor
import com.nayya.workhub.utils.mutable

class DistanceFromCityViewModel(
    private val conditionSelectionVacancyInteractor: ConditionSelectionVacancyInteractor
) : ViewModel() {

    class Factory(
        private val conditionSelectionVacancyInteractor: ConditionSelectionVacancyInteractor

    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DistanceFromCityViewModel(
                conditionSelectionVacancyInteractor
            ) as T
        }
    }

    val distanceFromCityLiveData: LiveData<List<DistanceInKmEntity>> =
        MutableLiveData()

    val selectedDistanceFromCityLiveData: LiveData<String> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        conditionSelectionVacancyInteractor.getAllDistance {
            distanceFromCityLiveData.mutable().postValue(it)
        }

        conditionSelectionVacancyInteractor.getDistance {
            selectedDistanceFromCityLiveData.mutable().postValue(it.id)
        }
    }
}