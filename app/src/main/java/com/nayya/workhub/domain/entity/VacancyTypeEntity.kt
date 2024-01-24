package com.nayya.workhub.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyTypeEntity(
    @SerializedName("id")
    val id: String,

    @SerializedName("vacancy_type")
    val vacancyType: String,

    @SerializedName("vacancies_list")
    val vacanciesList: MutableList<VacancyEntity> = mutableListOf()

) : Parcelable
