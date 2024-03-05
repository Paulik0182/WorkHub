package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InlandLocation(

    @SerializedName("address")
    val address: Address?,

    @SerializedName("location")
    val location: Location?

) : Parcelable