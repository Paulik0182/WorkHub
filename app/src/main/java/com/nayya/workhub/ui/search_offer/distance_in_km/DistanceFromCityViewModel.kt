package com.nayya.workhub.ui.search_offer.distance_in_km

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo.ConditionSelectionVacancyRepo

class DistanceFromCityViewModel(
    private val conditionSelectionVacancyRepo: ConditionSelectionVacancyRepo
) : ViewModel() {

    class Factory(
        private val conditionSelectionVacancyRepo: ConditionSelectionVacancyRepo

    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DistanceFromCityViewModel(
                conditionSelectionVacancyRepo
            ) as T
        }
    }

    val distanceFromCityLiveData: LiveData<List<Pair<DistanceInKmEntity, Boolean>>> =
        MutableLiveData()


    init {
//        conditionSelectionVacancyRepo.getAllDistance {
//            distanceFromCityLiveData.mutable().postValue(it)
//        }
    }
}