package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(

    @SerializedName("code")
    val code: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("pracujPlName")
    val pracujPlName: String?,

    @SerializedName("primaryTargetSiteName")
    val primaryTargetSiteName: String?

) : Parcelable