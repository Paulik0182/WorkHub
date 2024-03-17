package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parent(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?

) : Parcelable