package com.nayya.workhub.ui.search_offer.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.LocationEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionInteractor
import com.nayya.workhub.utils.mutable

class LocationViewModel(
    private val locationSelectionInteractor: LocationSelectionInteractor
) : ViewModel() {

    class Factory(
        private val locationSelectionInteractor: LocationSelectionInteractor,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LocationViewModel(
                locationSelectionInteractor,
            ) as T
        }
    }

    val locationLiveData: LiveData<List<Pair<LocationEntity, Boolean>>> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        locationSelectionInteractor.getLocations {
            locationLiveData.mutable().postValue(it)
        }
    }
}