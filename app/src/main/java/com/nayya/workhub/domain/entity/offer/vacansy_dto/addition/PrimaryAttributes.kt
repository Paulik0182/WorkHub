package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrimaryAttributes(

    @SerializedName("code")
    val code: String?,

    @SerializedName("label")
    val label: LabelX?,

    @SerializedName("model")
    val model: ModelXXX?

) : Parcelable