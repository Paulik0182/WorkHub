package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Icon(

    @SerializedName("rasterUrl")
    val rasterUrl: String?,

    @SerializedName("vectorUrl")
    val vectorUrl: String?

) : Parcelable