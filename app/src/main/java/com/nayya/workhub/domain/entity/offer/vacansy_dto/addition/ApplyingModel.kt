package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApplyingModel(

    @SerializedName("formMode")
    val formMode: String?,

    @SerializedName("formUrl")
    val formUrl: String?,

    @SerializedName("multiApplying")
    val multiApplying: List<String>?,

    @SerializedName("optionalCv")
    val optionalCv: Boolean?

) : Parcelable