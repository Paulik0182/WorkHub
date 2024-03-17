package com.nayya.workhub.data.favorite

import com.nayya.workhub.domain.entity.favorite.FavoriteVacancyJobEntity
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity

fun VacancyJobEntity.mapToFavoriteVacancy(
    isFavorite: Boolean = false
): FavoriteVacancyJobEntity {
    return FavoriteVacancyJobEntity(
        key = this.key,

        nameCompany = this.nameCompany,

        labelCompany = this.labelCompany,

        titleVacancies = this.titleVacancies,

        categoryVacanciesList = this.categoryVacanciesList,

        financialProposalList = financialProposalList?.let { ArrayList(it) },

        countryList = this.countryList,

        cityList = this.cityList,

        offerValid = this.offerValid,

        deadlineEndOffer = this.deadlineEndOffer,

        contractOptionList = contractOptionList?.let { ArrayList(it) },

        employmentRate = employmentRate,

        specialistLevel = specialistLevel,

        typeWork = typeWork,

        gettingStarted = gettingStarted,

        interviewMethod = interviewMethod,

        dutiesEmployee = dutiesEmployee,

        requirementsEntityList = requirementsEntityList?.let { ArrayList(it) }
    )
}