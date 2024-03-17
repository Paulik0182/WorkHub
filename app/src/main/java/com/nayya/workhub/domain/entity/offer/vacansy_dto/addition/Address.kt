package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(

    @SerializedName("additionalInfo")
    val additionalInfo: String?,

    @SerializedName("buildingNumber")
    val buildingNumber: String?,

    @SerializedName("coordinates")
    val coordinates: Coordinates?,

    @SerializedName("district")
    val district: String?,

    @SerializedName("flatNumber")
    val flatNumber: String?,

    @SerializedName("street")
    val street: String?,

    @SerializedName("zipCode")
    val zipCode: String?

) : Parcelable