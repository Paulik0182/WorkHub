package com.nayya.workhub.ui.job_details.city_for_work

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import com.nayya.workhub.utils.mutable

class CityForWorkViewModel(
    private val pracujPlOffersJobRepo: PracujPlOffersJobRepo,
    private val groupId: String
) : ViewModel() {

    class Factory(
        private val pracujPlOffersJobRepo: PracujPlOffersJobRepo,
        private val groupId: String
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CityForWorkViewModel(
                pracujPlOffersJobRepo,
                groupId
            ) as T
        }
    }

    val cityLiveData: LiveData<List<OfferJob>> = MutableLiveData()
    val selectedCityLiveData: LiveData<OfferJob> = MutableLiveData()


    init {
        pracujPlOffersJobRepo.getCities(groupId) {
            cityLiveData.mutable().postValue(it)
        }
    }

    fun onCityClick(cityListItem: OfferJob) {
        selectedCityLiveData.mutable().postValue(cityListItem)
    }
}