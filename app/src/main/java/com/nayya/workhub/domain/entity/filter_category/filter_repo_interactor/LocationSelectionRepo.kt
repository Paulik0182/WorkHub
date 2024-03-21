package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

interface LocationSelectionRepo {

    fun getLocationSelectionIds(callback: (List<String>) -> Unit)

    fun setLocationSelection(id: String, selection: Boolean)
}