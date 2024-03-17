package com.nayya.workhub.domain.entity

data class VacancyQueryResponse(
    val queryString: String,
    val workEntities: MutableList<VacancyTypeEntity>
)