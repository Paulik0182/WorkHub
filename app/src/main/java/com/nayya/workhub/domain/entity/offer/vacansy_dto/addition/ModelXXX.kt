package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelXXX(

    @SerializedName("flag")
    val flag: Boolean?,

    @SerializedName("modelType")
    val modelType: String?,

    @SerializedName("number")
    val number: Int?

) : Parcelable