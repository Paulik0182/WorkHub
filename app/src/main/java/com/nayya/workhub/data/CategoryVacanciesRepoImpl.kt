package com.nayya.workhub.data

import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.repo.CategoryVacanciesRepo

class CategoryVacanciesRepoImpl : CategoryVacanciesRepo {
    override fun getCategoryVacancies(callback: (List<CategoryVacanciesEntity>) -> Unit) {
        val fakeData = getCategory()
        callback(fakeData)
    }

    private fun getCategory(): List<CategoryVacanciesEntity> {
        return listOf(
            CategoryVacanciesEntity("1", "IT"),
            CategoryVacanciesEntity("2", "Android"),
            CategoryVacanciesEntity("3", "Java"),
            CategoryVacanciesEntity("4", "Administrator"),
            CategoryVacanciesEntity("5", "Administrator DataBase"),
            CategoryVacanciesEntity("6", "UX/UI")
        )
    }
}