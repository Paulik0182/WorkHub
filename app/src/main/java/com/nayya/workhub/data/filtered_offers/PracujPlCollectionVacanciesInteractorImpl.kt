package com.nayya.workhub.data.filtered_offers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.CategoryX
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.interactor.PracujPlCollectionVacanciesInteractor
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.repo.PracujPlCategoryFilterRepo
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.utils.UsedConst

class PracujPlCollectionVacanciesInteractorImpl(
    private val context: Context,
    private val categorySelectionInteractor: CategorySelectionInteractor,
    private val pracujPlCategoryFilterRepo: PracujPlCategoryFilterRepo
) : PracujPlCollectionVacanciesInteractor {

    private val urlBasic = UsedConst.PracujPlHttpsConst.BASIC_PRACUJ_PL_URL_KEY

    override fun getPracujPlUrlCollectionVacancies(callback: (List<CategoryX>) -> Unit) {
        if (isConnected()) {
            categorySelectionInteractor.getCategories { categories ->
                val selectedCategories = categories
                    .filter { it.second } // Фильтруем только выбранные категории
                    .map { it.first.categoryName } // Получаем список выбранных категорий

                if (selectedCategories.isNotEmpty()) {
                    pracujPlCategoryFilterRepo.setCategoryFilter(urlBasic) { list ->


                        val subcategoryFilteredList = list.filter {
                            it.name in selectedCategories
                        }

                        val categoriesFilteredList = list.filter { category ->
                            subcategoryFilteredList.any {
                                it.parentId == category.id
                            }
                        }

                        val categoriesAndSubcategoryList =
                            (subcategoryFilteredList + categoriesFilteredList).distinct()


                        callback(categoriesAndSubcategoryList) // отобранный список по названию подкатегории
                    }

                } else {
                    callback(emptyList())
                }
            }
        } else {
            Toast.makeText(context, "НЕТ ИНТЕРНЕТА", Toast.LENGTH_SHORT).show()
            callback(emptyList())
        }
    }

    override fun compiledLineForFilteringByCategory(
        categoryList: List<CategoryX>,
        url: (String) -> Unit
    ) {

        val categoryUrlEncoded = categoryList.filter { it.parentId == null }
            .joinToString("/") { it.name?.replace(" ", "%20") ?: "" }

        val subcategoryIds = categoryList.filter {
            it.parentId != null
        }
            .map { it.id.toString() }
            .joinToString(",")

//            val url = "$urlBasic$categoryUrlEncoded;kw?cc=$subcategoryIds"
        val urlRequest = "$urlBasic?cc=$subcategoryIds"
        Log.d("@@@", "PracujPlCollectionVacanciesInteractorImpl. URL = $urlRequest")

        url(urlRequest)
    }

//    override fun getPracujPlUrlCollectionVacancies(callback: (List<CategoryX>) -> Unit) {
//        if (isConnected()) {
//            categorySelectionInteractor.getCategories { categories ->
//                val selectedCategories = categories
//                    .filter { it.second } // Фильтруем только выбранные категории
//                    .map { it.first.categoryName } // Получаем список выбранных категорий
//
//                if (selectedCategories.isNotEmpty()) {
//                        pracujPlCategoryFilterRepo.setCategoryFilter(urlBasic) { list ->
//                            val filteredList = list.flatMap {
//                                it.props?.pageProps?.data?.dictionaries?.categories ?: emptyList()
////                                it.props?.pageProps?.data?.dictionaries?.categories!!
//                            }.filter {
//                                it.name in selectedCategories
//                            }
//                            callback(filteredList) // список
//                        }
//                } else {
//                    callback(emptyList())
//                }
//            }
//        } else {
//            Toast.makeText(context, "НЕТ ИНТЕРНЕТА", Toast.LENGTH_SHORT).show()
//            callback(emptyList())
//        }
//    }

    override fun compiledLineForFilteringByCategory(): String {
        var nameCategory: String = "nameCategory"
        var id: String = ""
        var nameSubcategory: String = ""
        var level: String = ""
        var parentId: String = ""

        var stringForFilteringByCategory = ""

        getPracujPlUrlCollectionVacancies { categoryList ->
            categoryList.mapNotNull {
                id = it.id.toString()
                nameSubcategory = it.name ?: ""
                level = it.level.toString()
                parentId = it.parentId.toString()

            }
        }
        stringForFilteringByCategory = "/$nameCategory;cc,$parentId/$nameSubcategory;cc,$id"

        return stringForFilteringByCategory
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager
            .getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
}