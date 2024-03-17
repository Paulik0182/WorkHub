package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneX(

    @SerializedName("internationalValue")
    val internationalValue: String,

    @SerializedName("uri")
    val uri: String

) : Parcelable