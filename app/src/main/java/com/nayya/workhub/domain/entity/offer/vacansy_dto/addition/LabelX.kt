package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LabelX(

    @SerializedName("pracujPlText")
    val pracujPlText: String?,

    @SerializedName("primaryTargetSiteText")
    val primaryTargetSiteText: String?,

    @SerializedName("text")
    val text: String?

) : Parcelable