package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApplyProxy(

    @SerializedName("applyUrl")
    val applyUrl: String?,

    @SerializedName("multiApplying")
    val multiApplying: List<String>?,

    @SerializedName("multiApplyingEnabled")
    val multiApplyingEnabled: Boolean?

) : Parcelable