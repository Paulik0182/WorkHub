package com.nayya.workhub.ui.search_offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.LocationEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.DistanceConditionSelectionVacancyInteractor
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionInteractor
import com.nayya.workhub.utils.mutable

class SearchOfferViewModel(
    private val distanceConditionSelectionVacancyInteractor: DistanceConditionSelectionVacancyInteractor,
    private val locationSelectionInteractor: LocationSelectionInteractor
) : ViewModel() {

    class Factory(
        private val distanceConditionSelectionVacancyInteractor: DistanceConditionSelectionVacancyInteractor,
        private val locationSelectionInteractor: LocationSelectionInteractor

    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchOfferViewModel(
                distanceConditionSelectionVacancyInteractor,
                locationSelectionInteractor
            ) as T
        }
    }

    val distanceFromCityLiveData: LiveData<DistanceInKmEntity> =
        MutableLiveData()
    val locationLiveData: LiveData<List<Pair<LocationEntity, Boolean>>> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        distanceConditionSelectionVacancyInteractor.getDistance {
            distanceFromCityLiveData.mutable().postValue(it)
        }

        locationSelectionInteractor.getLocations {
            locationLiveData.mutable().postValue(it)
        }
    }
}