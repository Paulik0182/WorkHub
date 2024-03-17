package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAgreements(

    @SerializedName("gpc_ad")
    val gpcAd: String?,

    @SerializedName("gpc_analytic")
    val gpcAnalytic: String?,

    @SerializedName("gpc_audience")
    val gpcAudience: String?,

    @SerializedName("gpc_func")
    val gpcFunc: String?,

    @SerializedName("gpc_social")
    val gpcSocial: String?,

    @SerializedName("gpc_v")
    val gpcV: String?

) : Parcelable