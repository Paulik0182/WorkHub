package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeoContent(

    @SerializedName("popularCategories")
    val popularCategories: List<PopularCategory>?,

    @SerializedName("popularCities")
    val popularCities: List<PopularCity>?,

    @SerializedName("popularSearches")
    val popularSearches: List<PopularSearche>?

) : Parcelable