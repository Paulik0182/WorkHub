package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InformationClause(

    @SerializedName("longText")
    val longText: String?,

    @SerializedName("shortText")
    val shortText: String?,

    ) : Parcelable