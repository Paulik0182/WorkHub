package com.nayya.workhub.domain.entity.offer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfferListItem(

    @SerializedName("key")
    val key: String,

    @SerializedName("companyName")
    val companyName: String? = "",

    @SerializedName("companyLogoUri")
    val companyLogoUri: String? = null,

    @SerializedName("groupId")
    val groupId: String? = "",

    @SerializedName("jobTitle")
    val jobTitle: String? = "",

    @SerializedName("technologies")
    val technologies: List<String>? = null,

    @SerializedName("offers")
    val offers: List<OfferJob>? = null,

    @SerializedName("typesOfContract")
    val typesOfContract: List<String>? = null,

    @SerializedName("salaryDisplayText")
    val salaryDisplayText: String? = null,

    @SerializedName("workSchedules")
    val workSchedules: List<String>? = null,

    @SerializedName("workModes")
    val workModes: List<String>? = null,

    @SerializedName("positionLevels")
    val positionLevels: List<String>? = null,

    @SerializedName("companyProfileAbsoluteUri")
    val companyProfileAbsoluteUri: String? = null, // список предложений конкретной компании

//    val aboutProjectShortDescription: String? = "",
//    val appliedProducts: List<String>? = listOf(),
//    val commonOfferId: Any? = "",
//    val companyId: Int? = 0,

//    val desktopBannerUri: Any? = "",
//    val expirationDate: String? = "",
//    val groupId: String? = "",
//    val isFranchise: Boolean? = false,
//    val isJobiconCompany: Boolean? = false,
//    val isOneClickApply: Boolean? = false,
//    val isOptionalCv: Boolean? = false,
//    val isSuperOffer: Boolean? = false,
//    val jobDescription: String? = "",

//    val lastPublicated: String? = "",
//    val mobileBannerUri: String? = "",

//    val primaryAttributes: List<PrimaryAttribute>? = listOf(),
//    val searchEngineRelevancyScore: Int,

    var isFavorite: Boolean = false //нет в gson

) : Parcelable