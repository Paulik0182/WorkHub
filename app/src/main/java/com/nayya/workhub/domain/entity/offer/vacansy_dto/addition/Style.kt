package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class Style(

    @SerializedName("fontColor")
    val fontColor: FontColor?,

    @SerializedName("images")
    val images: List<String>?,

    @SerializedName("logo")
    val logo: String?,

    @SerializedName("sectionsBackground")
    val sectionsBackground: String?,

    @SerializedName("themeColor")
    val themeColor: ThemeColor?

) : Parcelable