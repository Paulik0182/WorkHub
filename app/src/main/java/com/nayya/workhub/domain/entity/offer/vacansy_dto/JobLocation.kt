package com.nayya.workhub.domain.entity.offer.vacansy_dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobLocation(

    @SerializedName("addressCountry")
    val addressCountry: String,

    @SerializedName("addressLocality")
    val addressLocality: String,

    @SerializedName("addressRegion")
    val addressRegion: String,

    @SerializedName("postalCode")
    val postalCode: String,

    @SerializedName("streetAddress")
    val streetAddress: String?

) : Parcelable