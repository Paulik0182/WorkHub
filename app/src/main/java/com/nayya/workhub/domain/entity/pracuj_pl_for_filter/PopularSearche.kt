package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularSearche(

    @SerializedName("absoluteUrl")
    val absoluteUrl: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?

) : Parcelable