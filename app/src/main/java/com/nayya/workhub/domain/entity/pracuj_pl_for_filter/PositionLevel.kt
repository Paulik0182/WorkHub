package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PositionLevel(

    @SerializedName("code")
    val code: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?

) : Parcelable