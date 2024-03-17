package com.nayya.workhub.ui.search_offer.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.utils.mutable

class FilterCategoryVacanciesViewModel(
    private val categorySelectionInteractor: CategorySelectionInteractor,
) : ViewModel() {

    class Factory(
        private val categorySelectionInteractor: CategorySelectionInteractor,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilterCategoryVacanciesViewModel(
                categorySelectionInteractor,
            ) as T
        }
    }

    val categoryVacanciesLiveData: LiveData<List<Pair<CategoryVacanciesEntity, Boolean>>> =
        MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        categorySelectionInteractor.getCategories {
            categoryVacanciesLiveData.mutable().postValue(it)
        }
    }
}