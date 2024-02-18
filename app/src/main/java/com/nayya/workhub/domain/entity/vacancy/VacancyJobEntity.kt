package com.nayya.workhub.domain.entity.vacancy

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyJobEntity(

    @SerializedName("key")
    val key: String,

    @SerializedName("mame_company")
    val nameCompany: String,

    @SerializedName("label_company")
    val labelCompany: String?,

    @SerializedName("title_vacancies")
    val titleVacancies: String,

    @SerializedName("category_vacancies_list")
    val categoryVacanciesList: List<CategoryVacanciesEntity>,

    @SerializedName("financial_proposal_list")
    val financialProposalList: List<FinancialProposalEntity?>,

    @SerializedName("country_list")
    val countryList: List<CountryEntity>,

    @SerializedName("city_list")
    val cityList: List<CityEntity>,

    @SerializedName("offer_valid")
    val offerValid: Int,

    @SerializedName("deadline_end_offer")
    val deadlineEndOffer: Long,

    @SerializedName("contract_option_list")
    val contractOptionList: List<ContractOptionEntity?>,

    @SerializedName("employment_rate")
    val employmentRate: String,

    @SerializedName("specialist_level")
    val specialistLevel: String,

    @SerializedName("type_work")
    val typeWork: String?,

    @SerializedName("getting_started")
    val gettingStarted: String?,

    @SerializedName("interview_method")
    val interviewMethod: String?,

    @SerializedName("duties_employee")
    val dutiesEmployee: String?

) : Parcelable
