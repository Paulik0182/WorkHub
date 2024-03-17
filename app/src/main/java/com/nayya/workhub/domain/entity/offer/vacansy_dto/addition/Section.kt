package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Section(

    @SerializedName("header")
    val header: String?,

    @SerializedName("id")
    val id: Id?,

    @SerializedName("model")
    val model: ModelX?,

    @SerializedName("number")
    val number: Int?,

    @SerializedName("scrollId")
    val scrollId: String?,

    @SerializedName("sectionType")
    val sectionType: String?,

    @SerializedName("subSections")
    val subSections: List<SubSection>?,

    @SerializedName("title")
    val title: String?

) : Parcelable