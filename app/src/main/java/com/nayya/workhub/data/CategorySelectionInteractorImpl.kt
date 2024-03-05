package com.nayya.workhub.data

import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.domain.repo.CategorySelectionRepo
import com.nayya.workhub.domain.repo.CategoryVacanciesRepo

class CategorySelectionInteractorImpl(
    private val categoryVacanciesRepo: CategoryVacanciesRepo,
    private val categorySelectionRepo: CategorySelectionRepo
) : CategorySelectionInteractor {

    override fun getCategories(callback: (List<Pair<CategoryVacanciesEntity, Boolean>>) -> Unit) {
        categoryVacanciesRepo.getCategoryVacancies { categoryList ->
            var list = categoryList.map { category ->
                Pair(category, false)
            }

            categorySelectionRepo.getCategorySelectionIds { selectionIdList ->
                selectionIdList.forEach { selectionId ->
                    list = list.map { categoryPair ->
                        if (categoryPair.first.id == selectionId) {
                            categoryPair.copy(second = true)
                        } else {
                            categoryPair
                        }
                    }
                }
                callback(list)
            }
        }
    }

    override fun setSelection(id: String, selection: Boolean) {
        categorySelectionRepo.setSelection(id, selection)
    }
}