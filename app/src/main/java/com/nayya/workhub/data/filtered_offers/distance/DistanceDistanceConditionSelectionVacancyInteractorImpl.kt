package com.nayya.workhub.data.filtered_offers.distance

import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.DistanceConditionSelectionVacancyInteractor
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.DistanceConditionSelectionVacancyRepo

class DistanceDistanceConditionSelectionVacancyInteractorImpl(
    private val distanceConditionSelectionVacancyRepo: DistanceConditionSelectionVacancyRepo
) : DistanceConditionSelectionVacancyInteractor {

    private var data: MutableList<DistanceInKmEntity> = mutableListOf()

    override fun getAllDistance(callback: (List<DistanceInKmEntity>) -> Unit) {
        callback(ArrayList(data))
    }

    override fun getDistance(callback: (DistanceInKmEntity) -> Unit) {

        distanceConditionSelectionVacancyRepo.getConditionSelectionIds {
            getAllDistance { distanceList ->
                callback(distanceList.first { distance ->
                    distance.id == it
                })
            }
        }
    }

    override fun setSelectionDistance(id: String) {
        distanceConditionSelectionVacancyRepo.setDistance(id)
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