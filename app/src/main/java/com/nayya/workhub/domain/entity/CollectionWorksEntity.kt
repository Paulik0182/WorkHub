package com.nayya.workhub.domain.entity

import android.os.Parcelable
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionWorksEntity(
    val vacancy: VacancyJobEntity,
    val works: MutableList<VacancyTypeEntity> = mutableListOf()
) : Parcelable
