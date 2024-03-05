package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Applying(

    @SerializedName("applyingModel")
    val applyingModel: ApplyingModel?,

    @SerializedName("applyingType")
    val applyingType: String?

) : Parcelable