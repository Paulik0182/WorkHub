package com.nayya.workhub.domain.entity.offer.vacansy_dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyHeadingEntity(

    @SerializedName("baseSalary")
    val baseSalary: BaseSalary?,

    @SerializedName("datePosted")
    val datePosted: String?,

    @SerializedName("employmentType")
    val employmentType: String?,

    @SerializedName("experienceRequirements")
    val experienceRequirements: String?,

    @SerializedName("hiringOrganization")
    val hiringOrganization: String?,

    @SerializedName("industry")
    val industry: String?,

    @SerializedName("jobBenefits")
    val jobBenefits: String?,

    @SerializedName("jobLocation")
    val jobLocation: JobLocation?,

    @SerializedName("responsibilities")
    val responsibilities: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("validThrough")
    val validThrough: String?

) : Parcelable