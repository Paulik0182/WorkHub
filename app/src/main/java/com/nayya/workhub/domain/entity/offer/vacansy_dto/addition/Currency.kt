package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(

    @SerializedName("code")
    val code: String?,

    @SerializedName("symbol")
    val symbol: String?

) : Parcelable