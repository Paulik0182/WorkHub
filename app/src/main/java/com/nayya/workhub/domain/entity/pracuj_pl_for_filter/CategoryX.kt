package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryX(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("level")
    val level: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("parentId")
    val parentId: Int?

) : Parcelable