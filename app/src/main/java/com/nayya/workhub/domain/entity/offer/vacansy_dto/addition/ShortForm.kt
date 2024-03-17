package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortForm(

    @SerializedName("name")
    val name: String?,

    @SerializedName("pracujPlName")
    val pracujPlName: String?

) : Parcelable