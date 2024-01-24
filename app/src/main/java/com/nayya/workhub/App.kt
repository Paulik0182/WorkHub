package com.nayya.workhub

import android.app.Application
import com.nayya.workhub.data.CollectionVacanciesInteractorImpl
import com.nayya.workhub.data.retrofit.VacanciesRepoImpl
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.repo.VacanciesRepo
import com.nayya.workhub.utils.MyDiy


class App : Application() {

    private val myDiy: MyDiy = MyDiy()

    private val vacanciesRepo: VacanciesRepo by lazy {
        VacanciesRepoImpl(myDiy.imdbApi)
    }

    val collectionVacanciesInteractor: CollectionVacanciesInteractor by lazy {
        CollectionVacanciesInteractorImpl(
            vacanciesTypeRepo = myDiy.vacanciesTypeRepo,
            vacanciesRepo = vacanciesRepo,
            context = this
        )
    }
}