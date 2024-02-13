package com.nayya.workhub

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.nayya.workhub.data.CategorySelectionInteractorImpl
import com.nayya.workhub.data.CollectionVacanciesInteractorImpl
import com.nayya.workhub.data.retrofit.VacanciesRepoImpl
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.repo.VacanciesRepo
import com.nayya.workhub.utils.MyDiy

class App : Application() {

    private val myDiy: MyDiy = MyDiy(this)

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