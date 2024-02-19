package com.nayya.workhub.domain.entity.vacancy

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryEntity(
    @SerializedName("key")
    val key: String,

    @SerializedName("country")
    val country: List<String>
) : Parcelable
