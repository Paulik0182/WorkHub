package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(

    @SerializedName("searchCriteria")
    val searchCriteria: String?, // при загрузке было Any

) : Parcelable