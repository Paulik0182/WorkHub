package com.nayya.workhub.data.filtered_offers

import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo.ConditionSelectionVacancyRepo

class ConditionSelectionVacancyRepoImpl : ConditionSelectionVacancyRepo {

    private var data: MutableList<DistanceInKmEntity> = mutableListOf()

    override fun getAllDistance(callback: (List<DistanceInKmEntity>) -> Unit) {
        callback(ArrayList(data))
    }

    override fun getDistance(callback: (List<Pair<DistanceInKmEntity, Boolean>>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun setSelectionDistance(id: String, selection: Boolean) {
        TODO("Not yet implemented")
    }

    init {
        data.add(DistanceInKmEntity("0", 0))
        data.add(DistanceInKmEntity("1", 10))
        data.add(DistanceInKmEntity("2", 20))
        data.add(DistanceInKmEntity("3", 30))
        data.add(DistanceInKmEntity("4", 40))
        data.add(DistanceInKmEntity("5", 50))
    }

}