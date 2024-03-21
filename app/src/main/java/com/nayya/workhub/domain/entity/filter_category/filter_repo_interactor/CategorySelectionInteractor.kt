package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

interface CategorySelectionInteractor {

    fun getCategories(callback: (List<Pair<CategoryVacanciesEntity, Boolean>>) -> Unit)

    fun setSelection(id: String, selection: Boolean)
}