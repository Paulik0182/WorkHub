package com.nayya.workhub.data.filtered_offers.location

import com.nayya.workhub.domain.entity.filter_category.LocationEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationRepo
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionInteractor
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionRepo

class LocationSelectionInteractorImpl(
    private val locationRepo: LocationRepo,
    private val locationSelectionRepo: LocationSelectionRepo
) : LocationSelectionInteractor {

    override fun getLocations(callback: (List<Pair<LocationEntity, Boolean>>) -> Unit) {
        locationRepo.getLocation { locationList ->
            var list = locationList.map { location ->
                Pair(location, false)
            }

            locationSelectionRepo.getLocationSelectionIds { selectionIdList ->
                selectionIdList.forEach { selectionId ->
                    list = list.map { categoryPair ->
                        if (categoryPair.first.id.toString() == selectionId) {
                            categoryPair.copy(second = true)
                        } else {
                            categoryPair
                        }
                    }
                }
                callback(list)
            }
        }
    }

    override fun setLocationSelection(id: String, selection: Boolean) {
        locationSelectionRepo.setLocationSelection(id, selection)
    }
}