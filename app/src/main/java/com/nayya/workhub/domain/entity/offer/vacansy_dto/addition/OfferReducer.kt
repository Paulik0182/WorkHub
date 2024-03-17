package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfferReducer(

    @SerializedName("clauseLongOpened")
    val clauseLongOpened: Boolean?,

    @SerializedName("favourite")
    val favourite: Boolean?,

    @SerializedName("lifecycle")
    val lifecycle: String?,

    @SerializedName("offer")
    val offer: Offer?,

    @SerializedName("revealedPhoneNumber")
    val revealedPhoneNumber: Boolean?

) : Parcelable