package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity

interface DistanceConditionSelectionVacancyInteractor {

    //    fun getAllDistance(): HashMap<String, DistanceInKmEntity>
//    fun getAllDistance(): List<DistanceInKmEntity>
    fun getAllDistance(callback: (List<DistanceInKmEntity>) -> Unit)

    fun getDistance(callback: (DistanceInKmEntity) -> Unit)

    fun setSelectionDistance(id: String)
}