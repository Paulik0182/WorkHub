package com.nayya.workhub.domain.entity.offer

data class VacancyJobDto(
    val title: String,
    val hiringOrganization: String,
    val jobBenefits: String? = ""
)