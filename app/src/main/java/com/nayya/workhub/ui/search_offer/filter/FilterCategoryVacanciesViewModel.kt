package com.nayya.workhub.ui.search_offer.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.utils.mutable

private const val SELECTED_CATEGORIES_KEY = "SELECTED_CATEGORIES_KEY"

class FilterCategoryVacanciesViewModel(
    private val categorySelectionInteractor: CategorySelectionInteractor,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    class Factory(
        private val categorySelectionInteractor: CategorySelectionInteractor,
        private val savedStateHandle: SavedStateHandle
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilterCategoryVacanciesViewModel(
                categorySelectionInteractor,
                savedStateHandle
            ) as T
        }
    }

    val categoryVacanciesLiveData: LiveData<List<Pair<CategoryVacanciesEntity, Boolean>>> =
        MutableLiveData()

    init {
        categorySelectionInteractor.getCategories {
            categoryVacanciesLiveData.mutable().postValue(it)
        }
    }

    fun saveSelectedCategories(selectedCategories: List<CategoryVacanciesEntity>) {
        savedStateHandle.set<List<CategoryVacanciesEntity>>(
            SELECTED_CATEGORIES_KEY,
            selectedCategories
        )
    }

    fun getSelectedCategories(): List<CategoryVacanciesEntity>? {
        return savedStateHandle.get<List<CategoryVacanciesEntity>>(SELECTED_CATEGORIES_KEY)
    }
}