package com.nayya.workhub.domain.entity.vacancy

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequirementsEntity(

    @SerializedName("key")
    val key: String,

    @SerializedName("requirements")
    val requirements: List<String>,

    @SerializedName("optional_benefits")
    val optionalBenefits: List<String>?

) : Parcelable
