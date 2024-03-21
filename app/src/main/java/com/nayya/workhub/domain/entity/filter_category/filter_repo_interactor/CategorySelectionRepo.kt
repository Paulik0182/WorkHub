package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

interface CategorySelectionRepo {

    fun getCategorySelectionIds(callback: (List<String>) -> Unit)

    fun setSelection(id: String, selection: Boolean)
}