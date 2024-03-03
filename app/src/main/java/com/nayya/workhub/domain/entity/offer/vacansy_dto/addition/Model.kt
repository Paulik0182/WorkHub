package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Model(

    @SerializedName("customItems")
    val customItems: List<CustomItem>?,

    @SerializedName("dictionaryName")
    val dictionaryName: String?,

    @SerializedName("items")
    val items: List<Item>?,

    @SerializedName("modelType")
    val modelType: String?

) : Parcelable