package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

interface ConditionSelectionVacancyRepo {

    fun getConditionSelectionIds(callback: (String) -> Unit)

    fun setDistance(id: String)

    fun getDistance(): String
}