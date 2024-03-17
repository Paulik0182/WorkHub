package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularCity(

    @SerializedName("absoluteUrl")
    val absoluteUrl: String?,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("offersCount")
    val offersCount: Int?,

    @SerializedName("offersCountQuery")
    val offersCountQuery: String?,

    @SerializedName("url")
    val url: String?

) : Parcelable