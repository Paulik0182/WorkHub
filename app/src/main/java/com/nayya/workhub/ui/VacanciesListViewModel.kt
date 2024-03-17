package com.nayya.workhub.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.CategoryX
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.PracujPlCategoryEntity
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.interactor.PracujPlCollectionVacanciesInteractor
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.repo.PracujPlCategoryFilterRepo
import com.nayya.workhub.utils.mutable

private const val MIN_NUMBER_ENTRIES = 49
class VacanciesListViewModel(
//    private val collectionVacanciesInteractor: CollectionVacanciesInteractor,
    private val pracujPlOffersJobRepo: PracujPlOffersJobRepo,
    private val pracujPlCategoryFilterRepo: PracujPlCategoryFilterRepo,
    private val pracujPlCollectionVacanciesInteractor: PracujPlCollectionVacanciesInteractor,
    private val context: Context
) : ViewModel() {

    private var currentPage = 1
    private var hasMoreData = true
    private var urlRequest: String? = null
    private var sizeEntries: Int = 0

    class Factory(
        private val pracujPlOffersJobRepo: PracujPlOffersJobRepo,
        private val pracujPlCategoryFilterRepo: PracujPlCategoryFilterRepo,
        private val pracujPlCollectionVacanciesInteractor: PracujPlCollectionVacanciesInteractor,
        private val context: Context
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VacanciesListViewModel(
                pracujPlOffersJobRepo,
                pracujPlCategoryFilterRepo,
                pracujPlCollectionVacanciesInteractor,
                context
            ) as T
        }
    }

    val vacanciesLiveData: LiveData<List<OfferListItem>> = MutableLiveData()
    val pracujPlCategoryFilterLiveData: LiveData<List<PracujPlCategoryEntity>> = MutableLiveData()
    val selectedVacancyJobLiveData: LiveData<OfferListItem> = MutableLiveData()
    val selectedСategoryJobLiveData: LiveData<List<CategoryX>> = MutableLiveData()

    fun onScrollFinish() {

        // todo Пересмотреть логику. Данный If нужен при загрузки менее 50 элементов,
        //  Без этого - сайт меняет URL на стартовый и фильтрация уже не верная. + Проблема - перемешиваются Лейбл
        if (sizeEntries <= MIN_NUMBER_ENTRIES) hasMoreData = false
        Log.d("@@@", "onScrollFinish(). NUMBER: $sizeEntries, $hasMoreData")

        if (hasMoreData) {
            currentPage++

            var urlPage = urlRequest + "&pn=$currentPage"

            Log.d("@@@", "onScrollFinish() called $urlPage")

            pracujPlOffersJobRepo.extractOffers(urlPage) {
                if (it.isEmpty()) {
                    hasMoreData = false
                    Toast.makeText(context, "Данных нет", Toast.LENGTH_SHORT).show()
                    Log.d("@@@", "onScrollFinish() called $it")
                } else {
                    val tempList = (vacanciesLiveData.value as MutableList) + it
                    vacanciesLiveData.mutable().postValue(tempList)
                    sizeEntries =
                        it.size // todo переприсвоили значения что бы небыло дозагрузки непонятными элементами
                }
            }
        }
    }

    init {
//        collectionVacanciesInteractor.getCollectionVacancies {
//            vacanciesLiveData.mutable().postValue(it)
//        }

//        pracujPlCategoryFilterRepo.setCategoryFilter(urlBasic) {
//            pracujPlCategoryFilterLiveData.mutable().postValue(it)
//        }

        pracujPlCollectionVacanciesInteractor.getPracujPlUrlCollectionVacancies { categoryList ->
            selectedСategoryJobLiveData.mutable().postValue(categoryList)

            pracujPlCollectionVacanciesInteractor.compiledLineForFilteringByCategory(categoryList) {
                urlRequest = it
            }

            pracujPlOffersJobRepo.extractOffers(urlRequest!!) {
                vacanciesLiveData.mutable().postValue(it)
                sizeEntries = it.size

                Log.d("@@@", "VacanciesListViewModel. URL = $urlRequest")
            }
        }


//        pracujPlCollectionVacanciesInteractor.getPracujPlUrlCollectionVacancies { categoryList ->
//            selectedСategoryJobLiveData.mutable().postValue(categoryList)
//            val category = categoryList.filter { it.parentId == null }
//            val subcategory = categoryList.filter { it.parentId != null }
//
//            val urlList = mutableListOf<String>()
//
//            for (cat in category) {
//                val categoryName = cat.name?.replace(" ", "%20") ?: ""
//                val categoryId = cat.id
//                val subcategories = subcategory.filter { it.parentId == categoryId }
//
//                for (subcat in subcategories) {
//                    val subcategoryName = subcat.name?.replace(" ", "%20") ?: ""
//                    val subcategoryId = subcat.id
//
//                    val url = "https://www.pracuj.pl/praca/$categoryName;cc,$categoryId/$subcategoryName;cc,$subcategoryId"
//                    urlList.add(url)
//                }
//            }
//
//            // Вывод списка URL-адресов
//            urlList.forEach { url -> Log.d("@@@", "VacanciesListViewModel. URL = $url") }
//        }

//        pracujPlCollectionVacanciesInteractor.getPracujPlUrlCollectionVacancies {categoryList->
//            selectedСategoryJobLiveData.mutable().postValue(categoryList)
//            var nameCategory= ""
//
//            val category = categoryList.filter {
//                it.parentId == null
//            }
//
//            val subcategory = categoryList.filter {
//                it.parentId != null
//            }
//            category.mapNotNull {
//                nameCategory= it.name?: ""
//            }
//
//
//            subcategory.mapNotNull {
//                val nameSubcategory = it.name
//                val id = it.id
//                it.level
//                val parentId = it.parentId
//
//                urlBasic = "$urlBasic${"/$nameCategory;cc,$parentId/$nameSubcategory;cc,$id"}"
//            }
//
//            Log.d("@@@", "VacanciesListViewModel. URL = $urlBasic")
//        }


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