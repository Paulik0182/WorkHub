package com.nayya.workhub.ui.job_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOfferVacancyRepo
import com.nayya.workhub.domain.entity.offer.vacansy_dto.VacancyHeadingEntity
import com.nayya.workhub.domain.entity.offer.vacansy_dto.addition.VacancyAdditionEntity
import com.nayya.workhub.utils.mutable

class JobDetailsViewModel(
//    private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
//    private val vacancyJobId: String,
//    private val favoriteCollectionVacanciesJobInteractor: FavoriteCollectionVacanciesJobInteractor,
//    private val offerFavoriteRepo: OfferFavoriteRepo,
    private val pracujPlOfferVacancyRepo: PracujPlOfferVacancyRepo,
    private val offerListItem: OfferJob?
) : ViewModel() {


//    fun onFavoriteChange(vacancyJobEntity: VacancyJobEntity) {
//        collectionVacanciesInteractor.setFavorite(
//            vacancyJobEntity.key,
//            !vacancyJobEntity.isFavorite
//        )
//    }

    class Factory(
        private val pracujPlOfferVacancyRepo: PracujPlOfferVacancyRepo,
        private val offerListItem: OfferJob?
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return JobDetailsViewModel(
                pracujPlOfferVacancyRepo,
                offerListItem
            ) as T
        }
    }

    val vacancyJobHeadingLiveData: LiveData<VacancyHeadingEntity> = MutableLiveData()
    val vacancyJobAdditionLiveData: LiveData<VacancyAdditionEntity> = MutableLiveData()
//    val favoriteVacancyJobLiveData: LiveData<FavoriteVacancyJobEntity> = MutableLiveData()

    init {

        val offerUrl = offerListItem?.offerAbsoluteUri


        if (vacancyJobHeadingLiveData.value == null) {
            pracujPlOfferVacancyRepo.extractOfferHeading(offerUrl!!) {
                it.let {
                    vacancyJobHeadingLiveData.mutable().postValue(it)
                }
            }
        }

        if (vacancyJobAdditionLiveData.value == null) {
            pracujPlOfferVacancyRepo.extractOfferAddition(offerUrl!!) {
                it.let {
                    vacancyJobAdditionLiveData.mutable().postValue(it)
                }
            }
        }

//        if (favoriteVacancyJobLiveData.value == null) {
//            favoriteCollectionVacanciesJobInteractor.getVacancyJob(vacancyJobId) {
//                it.let {
//                    favoriteVacancyJobLiveData.mutable().postValue(it)
//                }
//            }
//        }
    }
}