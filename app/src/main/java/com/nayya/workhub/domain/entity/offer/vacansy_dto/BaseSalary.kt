package com.nayya.workhub.domain.entity.offer.vacansy_dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseSalary(

    @SerializedName("currency")
    val currency: String?,

    @SerializedName("maxValue")
    val maxValue: Int?,

    @SerializedName("minValue")
    val minValue: Int?,

    @SerializedName("value")
    val value: String?

) : Parcelable