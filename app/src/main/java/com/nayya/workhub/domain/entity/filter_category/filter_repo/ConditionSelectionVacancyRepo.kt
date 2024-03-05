package com.nayya.workhub.domain.entity.filter_category.filter_repo

import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity

interface ConditionSelectionVacancyRepo {

    //    fun getAllDistance(): HashMap<String, DistanceInKmEntity>
//    fun getAllDistance(): List<DistanceInKmEntity>
    fun getAllDistance(callback: (List<DistanceInKmEntity>) -> Unit)

    fun getDistance(callback: (List<Pair<DistanceInKmEntity, Boolean>>) -> Unit)

    fun setSelectionDistance(id: String, selection: Boolean)
}