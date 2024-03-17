package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PageProps(

    @SerializedName("businessVariables")
    val businessVariables: BusinessVariables?,

    @SerializedName("Data")
    val `data`: Data?,

    @SerializedName("dehydratedState")
    val dehydratedState: DehydratedState?,

    @SerializedName("langCode")
    val langCode: String?,

    @SerializedName("user")
    val user: String?,  // при загрузке был Any

    @SerializedName("userAgent")
    val userAgent: String?,

    @SerializedName("userAgreements")
    val userAgreements: UserAgreements?,

    @SerializedName("userCookieString")
    val userCookieString: String?,

    @SerializedName("userIdentifier")
    val userIdentifier: String?  // при загрузке был Any

) : Parcelable