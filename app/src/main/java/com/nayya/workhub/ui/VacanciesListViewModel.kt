package com.nayya.workhub.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import com.nayya.workhub.utils.UsedConst
import com.nayya.workhub.utils.mutable

class VacanciesListViewModel(
//    private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
    private val pracujPlOffersJobRepo: PracujPlOffersJobRepo,
    private val context: Context
) : ViewModel() {

    private var currentPage = 1
    private var hasMoreData = true
    private val url = UsedConst.PracujPlHttpsConst.BASIC_URL_KEY

    class Factory(
        private val pracujPlOffersJobRepo: PracujPlOffersJobRepo,
        private val context: Context
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VacanciesListViewModel(pracujPlOffersJobRepo, context) as T
        }
    }

    val vacanciesLiveData: LiveData<List<OfferListItem>> = MutableLiveData()
    val selectedVacancyJobLiveData: LiveData<OfferListItem> = MutableLiveData()

    fun onScrollFinish() {
        if (hasMoreData) {
            currentPage++
            pracujPlOffersJobRepo.extractOffers(url + "?pn=$currentPage") {
                if (it.isEmpty()) {
                    hasMoreData = false
                    Toast.makeText(context, "Данных нет", Toast.LENGTH_SHORT).show()
                } else {
                    val tempList = (vacanciesLiveData.value as MutableList) + it
                    vacanciesLiveData.mutable().postValue(tempList)
                }
            }
        }
    }

    init {
//        collectionVacanciesInteractor.getCollectionVacancies {
//            vacanciesLiveData.mutable().postValue(it)
//        }
        pracujPlOffersJobRepo.extractOffers(url) {
            vacanciesLiveData.mutable().postValue(it)
        }
    }

    fun onVacancyJobClick(offerListItem: OfferListItem) {
        selectedVacancyJobLiveData.mutable().postValue(offerListItem)
    }

//    fun onDeleteVacancy(jobId: String) {
//        collectionVacanciesInteractor.delete(jobId)
//        collectionVacanciesInteractor.getCollectionVacancies {
//            vacanciesLiveData.mutable().postValue(it)
//        }
//    }
}