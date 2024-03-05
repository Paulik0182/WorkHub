package com.nayya.workhub.domain.entity.favorite

import android.os.Parcelable
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.entity.vacancy.CityEntity
import com.nayya.workhub.domain.entity.vacancy.ContractOptionEntity
import com.nayya.workhub.domain.entity.vacancy.CountryEntity
import com.nayya.workhub.domain.entity.vacancy.FinancialProposalEntity
import com.nayya.workhub.domain.entity.vacancy.RequirementsEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteVacancyJobEntity(

    val key: String,

    val nameCompany: String,

    val labelCompany: String?,

    val titleVacancies: String,

    val categoryVacanciesList: List<CategoryVacanciesEntity>,

    val financialProposalList: List<FinancialProposalEntity>? = null,

    val countryList: List<CountryEntity>,

    val cityList: List<CityEntity>,

    val offerValid: Int,

    val deadlineEndOffer: Long,

    val contractOptionList: List<ContractOptionEntity>? = null,

    val employmentRate: String,

    val specialistLevel: String,

    val typeWork: String?,

    val gettingStarted: String?,

    val interviewMethod: String?,

    val dutiesEmployee: String?,

    val requirementsEntityList: List<RequirementsEntity>?,

    var isFavorite: Boolean = false

) : Parcelable
