package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SecondaryAttribute(

    @SerializedName("code")
    val code: String?,

    @SerializedName("label")
    val label: Label?,

    @SerializedName("model")
    val model: Model?

) : Parcelable