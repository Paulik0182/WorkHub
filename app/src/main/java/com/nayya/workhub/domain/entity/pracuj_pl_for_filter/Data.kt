package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(

    @SerializedName("advicesCategories")
    val advicesCategories: AdvicesCategories?,

    @SerializedName("defaultSubservice")
    val defaultSubservice: String?,

    @SerializedName("dictionaries")
    val dictionaries: Dictionaries?,

    @SerializedName("mainOffersCounter")
    val mainOffersCounter: MainOffersCounter?,

    @SerializedName("seoContent")
    val seoContent: SeoContent?

) : Parcelable