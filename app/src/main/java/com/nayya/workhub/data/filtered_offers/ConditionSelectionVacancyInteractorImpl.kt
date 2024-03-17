package com.nayya.workhub.data.filtered_offers

import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.ConditionSelectionVacancyInteractor
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.ConditionSelectionVacancyRepo

class ConditionSelectionVacancyInteractorImpl(
    private val conditionSelectionVacancyRepo: ConditionSelectionVacancyRepo
) : ConditionSelectionVacancyInteractor {

    private var data: MutableList<DistanceInKmEntity> = mutableListOf()

    override fun getAllDistance(callback: (List<DistanceInKmEntity>) -> Unit) {
        callback(ArrayList(data))
    }

    override fun getDistance(callback: (DistanceInKmEntity) -> Unit) {

        conditionSelectionVacancyRepo.getConditionSelectionIds {
            getAllDistance { distanceList ->
                callback(distanceList.first { distance ->
                    distance.id == it
                })
            }
        }
    }

    override fun setSelectionDistance(id: String) {
        conditionSelectionVacancyRepo.setDistance(id)
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