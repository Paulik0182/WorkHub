package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dictionaries(

    @SerializedName("agglomerations")
    val agglomerations: List<String>?, // был List<Any>

    @SerializedName("bluePopular")
    val bluePopular: List<BluePopular>?,

    @SerializedName("categories")
    val categories: List<CategoryX>?,

    @SerializedName("contractTypes")
    val contractTypes: List<ContractType>?,

    @SerializedName("countries")
    val countries: List<Country>?,

    @SerializedName("foreignLocations")
    val foreignLocations: List<String>?, // был List<Any>

    @SerializedName("itSpecializations")
    val itSpecializations: List<ItSpecialization>?,

    @SerializedName("itTechnologies")
    val itTechnologies: List<ItTechnology>?,

    @SerializedName("itTechnologiesPosition")
    val itTechnologiesPosition: List<ItTechnologiesPosition>?,

    @SerializedName("locations")
    val locations: List<String>?, // был List<Any>

    @SerializedName("periods")
    val periods: List<Period>?,

    @SerializedName("positionLevels")
    val positionLevels: List<PositionLevel>?,

    @SerializedName("regions")
    val regions: List<Region>?,

    @SerializedName("timeUnits")
    val timeUnits: List<TimeUnit>?,

    @SerializedName("workModes")
    val workModes: List<WorkMode>?,

    @SerializedName("workSchedules")
    val workSchedules: List<WorkSchedule>?

) : Parcelable