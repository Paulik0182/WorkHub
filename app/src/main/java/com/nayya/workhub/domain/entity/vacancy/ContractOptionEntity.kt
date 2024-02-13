package com.nayya.workhub.domain.entity.vacancy

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContractOptionEntity(

    @SerializedName("key")
    val key: String,

    @SerializedName("country")
    val contractOption: List<String>

) : Parcelable
