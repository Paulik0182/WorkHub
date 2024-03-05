package com.nayya.workhub.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyEntity(

    @SerializedName("key")
    val key: String,

    @SerializedName("vacancy")
    val vacancy: String,

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("salary")
    val salary: String? = "",

    @SerializedName("company")
    val company: String? = ""

) : Parcelable
