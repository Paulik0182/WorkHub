package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Id(

    @SerializedName("number")
    val number: Int?,

    @SerializedName("type")
    val type: String?

) : Parcelable