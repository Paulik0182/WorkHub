package com.nayya.workhub.domain.entity.favorite

import android.os.Parcelable
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteCollectionEntity(
    val categoryVacanciesList: List<CategoryVacanciesEntity>,
    val vacanciesJob: MutableList<FavoriteVacancyJobEntity> = mutableListOf()
) : Parcelable
