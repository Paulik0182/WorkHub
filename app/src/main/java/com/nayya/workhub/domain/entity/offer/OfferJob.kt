package com.nayya.workhub.domain.entity.offer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class OfferJob(
    @SerializedName("partitionId")
    val partitionId: Int? = 0,

    @SerializedName("displayWorkplace")
    val displayWorkplace: String? = "",

    @SerializedName("isWholePoland")
    val isWholePoland: Boolean? = false,

    @SerializedName("offerAbsoluteUri")
    val offerAbsoluteUri: String? = null,

    ) : Parcelable