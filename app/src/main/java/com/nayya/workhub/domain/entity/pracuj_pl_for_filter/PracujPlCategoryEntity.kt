package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PracujPlCategoryEntity(

    @SerializedName("appGip")
    val appGip: Boolean?,

    @SerializedName("assetPrefix")
    val assetPrefix: String?,

    @SerializedName("buildId")
    val buildId: String?,

    @SerializedName("gssp")
    val gssp: Boolean?,

    @SerializedName("isFallback")
    val isFallback: Boolean?,

    @SerializedName("page")
    val page: String?,

    @SerializedName("props")
    val props: Props?,

    @SerializedName("query")
    val query: Query?, // при первой загрузки класс не имел структуры

    @SerializedName("runtimeConfig")
    val runtimeConfig: RuntimeConfig?,

    @SerializedName("scriptLoader")
    val scriptLoader: List<String>? // при загрузке было List<Any>

) : Parcelable