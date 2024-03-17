package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItSpecialization(

    @SerializedName("code")
    val code: String?,

    @SerializedName("name")
    val name: String?

) : Parcelable