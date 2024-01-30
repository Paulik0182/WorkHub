package com.nayya.workhub.domain.interactor

import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

interface CategorySelectionInteractor {

    fun getCategories(callback: (List<Pair<CategoryVacanciesEntity, Boolean>>) -> Unit)
}