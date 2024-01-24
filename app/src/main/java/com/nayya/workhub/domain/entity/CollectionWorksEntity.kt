package com.nayya.workhub.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionWorksEntity(
    val vacancy: VacancyEntity,
    val works: MutableList<VacancyTypeEntity> = mutableListOf()
) : Parcelable
