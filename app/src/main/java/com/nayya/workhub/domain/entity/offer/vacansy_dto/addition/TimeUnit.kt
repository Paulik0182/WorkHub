package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeUnit(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("longForm")
    val longForm: LongForm?,

    @SerializedName("shortForm")
    val shortForm: ShortForm?

) : Parcelable