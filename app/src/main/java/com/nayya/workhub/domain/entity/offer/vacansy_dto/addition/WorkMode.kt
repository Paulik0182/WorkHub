package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkMode(

    @SerializedName("code")
    val code: String?,

    @SerializedName("subText")
    val subText: String?,

    @SerializedName("text")
    val text: String?

) : Parcelable