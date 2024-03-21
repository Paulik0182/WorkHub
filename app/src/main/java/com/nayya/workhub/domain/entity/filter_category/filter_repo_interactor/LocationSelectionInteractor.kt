package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

import com.nayya.workhub.domain.entity.filter_category.LocationEntity

interface LocationSelectionInteractor {

    fun getLocations(callback: (List<Pair<LocationEntity, Boolean>>) -> Unit)

    fun setLocationSelection(id: String, selection: Boolean)
}