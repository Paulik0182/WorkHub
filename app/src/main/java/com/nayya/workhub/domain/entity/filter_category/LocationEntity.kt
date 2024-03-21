package com.nayya.workhub.domain.entity.filter_category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationEntity(

    @SerializedName("id")
    val id: Int,

    @SerializedName("locality")
    val locality: String

) : Parcelable