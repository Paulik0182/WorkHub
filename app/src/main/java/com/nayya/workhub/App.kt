package com.nayya.workhub

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.nayya.workhub.data.CategorySelectionInteractorImpl
import com.nayya.workhub.data.CollectionVacanciesInteractorImpl
import com.nayya.workhub.data.favorite.FavoriteCollectionVacanciesJobInteractorImpl
import com.nayya.workhub.data.filtered_offers.ConditionSelectionVacancyRepoImpl
import com.nayya.workhub.data.filtered_offers.PracujPlOfferVacancyRepoImpl
import com.nayya.workhub.data.filtered_offers.PracujPlOffersJobRepoImpl
import com.nayya.workhub.data.retrofit.VacanciesRepoImpl
import com.nayya.workhub.domain.entity.filter_category.filter_repo.ConditionSelectionVacancyRepo
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOfferVacancyRepo
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.interactor.FavoriteCollectionVacanciesJobInteractor
import com.nayya.workhub.domain.repo.VacanciesRepo
import com.nayya.workhub.utils.MyDiy

class App : Application() {

    val myDiy: MyDiy = MyDiy(this)

    private val vacanciesRepo: VacanciesRepo by lazy {
        VacanciesRepoImpl(myDiy.imdbApi)
    }

    val collectionVacanciesInteractor: CollectionVacanciesInteractor by lazy {
        CollectionVacanciesInteractorImpl(
            vacanciesTypeRepo = myDiy.vacanciesTypeRepo,
            vacanciesRepo = vacanciesRepo,
            context = this,
            categorySelectionInteractor = categorySelectionInteractor
        )
    }

    val categorySelectionInteractor: CategorySelectionInteractor by lazy {
        CategorySelectionInteractorImpl(
            categoryVacanciesRepo = myDiy.categoryVacanciesRepo,
            categorySelectionRepo = myDiy.categorySelectionRepo
        )
    }

    val favoriteCollectionVacanciesJobInteractor: FavoriteCollectionVacanciesJobInteractor by lazy {
        FavoriteCollectionVacanciesJobInteractorImpl(
            collectionVacanciesInteractor = collectionVacanciesInteractor,
            offerFavoriteRepo = myDiy.offerFavoriteRepo
        )
    }

    val pracujPlOffersJobRepo: PracujPlOffersJobRepo by lazy {
        PracujPlOffersJobRepoImpl(
            context = this
        )
    }

    val pracujPlOfferVacancyRepo: PracujPlOfferVacancyRepo by lazy {
        PracujPlOfferVacancyRepoImpl(
            context = this
        )
    }

    val conditionSelectionVacancyRepo: ConditionSelectionVacancyRepo by lazy {
        ConditionSelectionVacancyRepoImpl()
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        _instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}