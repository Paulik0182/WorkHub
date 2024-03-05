package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breadcrumb(

    @SerializedName("absoluteUrl")
    val absoluteUrl: String?,

    @SerializedName("children")
    val children: List<Children>?,

    @SerializedName("label")
    val label: String?

) : Parcelable