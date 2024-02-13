package com.nayya.workhub.ui.job_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.utils.mutable

class JobDetailsViewModel(
    private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
    private val vacancyJobId: String
) : ViewModel() {

    class Factory(
        private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
        private val vacancyJobId: String
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return JobDetailsViewModel(collectionVacanciesInteractor, vacancyJobId) as T
        }
    }

    val vacancyJobLiveData: LiveData<VacancyJobEntity> = MutableLiveData()

    init {
        if (vacancyJobLiveData.value == null) {
            collectionVacanciesInteractor.getVacancyJob(vacancyJobId) {
                it.let {
                    vacancyJobLiveData.mutable().postValue(it)
                }
            }
        }
    }
}