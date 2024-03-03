package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Offer(

    @SerializedName("appType")
    val appType: Int?,

    @SerializedName("applyLink")
    val applyLink: String?,

    @SerializedName("applyProxy")
    val applyProxy: ApplyProxy?,

    @SerializedName("applyTypeId")
    val applyTypeId: Int?,

    @SerializedName("applying")
    val applying: Applying?,

    @SerializedName("categories")
    val categories: List<Category>?,

    @SerializedName("clause")
    val clause: String?,

    @SerializedName("commonOfferId")
    val commonOfferId: String?,

    @SerializedName("companyId")
    val companyId: Int?,

    @SerializedName("countryId")
    val countryId: Int?,

    @SerializedName("countryName")
    val countryName: String?,

    @SerializedName("dateOfInitialPublication")
    val dateOfInitialPublication: String?,

    @SerializedName("employerName")
    val employerName: String?,

    @SerializedName("expirationDate")
    val expirationDate: String?,

    @SerializedName("hasClauseInfo")
    val hasClauseInfo: String?,

    @SerializedName("informationClause")
    val informationClause: InformationClause?,

    @SerializedName("isMultiAts")
    val isMultiAts: Boolean?,

    @SerializedName("isRemoteRecruitment")
    val isRemoteRecruitment: Boolean?,

    @SerializedName("isWholePoland")
    val isWholePoland: Boolean?,

    @SerializedName("jobOfferLanguage")
    val jobOfferLanguage: JobOfferLanguage?,

    @SerializedName("jobTitle")
    val jobTitle: String?,

    @SerializedName("jobiconCompanyInfo")
    val jobiconCompanyInfo: String?,

    @SerializedName("locationName")
    val locationName: String?,

    @SerializedName("multiAtsLocations")
    val multiAtsLocations: List<String>?,

    @SerializedName("offerId")
    val offerId: Int?,

    @SerializedName("offerRelativeUri")
    val offerRelativeUri: String?,

    @SerializedName("offerURLName")
    val offerURLName: String?,

    @SerializedName("oneClickApply")
    val oneClickApply: Boolean?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("positionLevelsName")
    val positionLevelsName: String?,

    @SerializedName("primaryAttributes")
    val primaryAttributes: List<PrimaryAttributes>?,

    @SerializedName("referenceNumber")
    val referenceNumber: String?,

    @SerializedName("regionId")
    val regionId: Int?,

    @SerializedName("regionName")
    val regionName: String?,

    @SerializedName("remoteWork")
    val remoteWork: Boolean?,

    @SerializedName("requireCV")
    val requireCV: Boolean?,

    @SerializedName("secondaryAttributes")
    val secondaryAttributes: List<SecondaryAttribute>?,

    @SerializedName("sections")
    val sections: List<Section>?,

    @SerializedName("style")
    val style: Style?,

    @SerializedName("template")
    val template: String?,

    @SerializedName("terminated")
    val terminated: Boolean?,

    @SerializedName("textTemplate")
    val textTemplate: String?,

    @SerializedName("typesOfContracts")
    val typesOfContracts: List<TypesOfContract>?,

    @SerializedName("version")
    val version: String?,

    @SerializedName("workModes")
    val workModes: List<WorkMode>?,

    @SerializedName("workSchedules")
    val workSchedules: String?,

    @SerializedName("workplaces")
    val workplaces: List<Workplace>?

) : Parcelable