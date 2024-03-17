package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Period(

    @SerializedName("key")
    val key: String?,

    @SerializedName("value")
    val value: String?

) : Parcelable