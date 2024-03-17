package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdvicesCategories(

    @SerializedName("categories")
    val categories: List<Category>?

) : Parcelable