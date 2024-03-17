package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeUnit(

    @SerializedName("code")
    val code: String?,

    @SerializedName("id:")
    val id: Int?,

    @SerializedName("longForm")
    val longForm: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("shortForm")
    val shortForm: String?

) : Parcelable