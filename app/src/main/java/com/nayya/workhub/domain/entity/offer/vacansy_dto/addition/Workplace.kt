package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Workplace(

    @SerializedName("abroadAddress")
    val abroadAddress: String? = "",

    @SerializedName("country")
    val country: Country?,

    @SerializedName("displayAddress")
    val displayAddress: String?,

    @SerializedName("inlandLocation")
    val inlandLocation: InlandLocation?,

    @SerializedName("isAbroad")
    val isAbroad: Boolean?,

    @SerializedName("region")
    val region: Region?

) : Parcelable