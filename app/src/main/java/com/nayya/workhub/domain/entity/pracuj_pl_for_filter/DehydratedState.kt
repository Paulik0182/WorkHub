package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DehydratedState(

    @SerializedName("mutations")
    val mutations: List<String>?, // был List<Any>

    @SerializedName("queries")
    val queries: List<String>? // был List<Any>

) : Parcelable