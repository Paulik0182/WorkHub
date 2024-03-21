package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

interface CategoryVacanciesRepo {

    fun getCategoryVacancies(callback: (List<CategoryVacanciesEntity>) -> Unit)
}