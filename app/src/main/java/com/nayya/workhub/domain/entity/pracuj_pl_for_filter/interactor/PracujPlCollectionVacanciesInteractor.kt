package com.nayya.workhub.domain.entity.pracuj_pl_for_filter.interactor

import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.CategoryX

interface PracujPlCollectionVacanciesInteractor {

    fun getPracujPlUrlCollectionVacancies(callback: (List<CategoryX>) -> Unit)
    fun compiledLineForFilteringByCategory(): String

    fun compiledLineForFilteringByCategory(categoryList: List<CategoryX>, url: (String) -> Unit)

//    fun getPracujPlVacancyJob(id: String, callback: (VacancyJobEntity?) -> Unit)
//    fun delete(jobId: String)

}