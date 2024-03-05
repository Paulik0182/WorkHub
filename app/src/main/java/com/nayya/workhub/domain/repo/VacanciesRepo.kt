package com.nayya.workhub.domain.repo

import com.nayya.workhub.domain.entity.VacancyTypeEntity

interface VacanciesRepo {

    fun getVacancies(vacancy: List<String>, callback: (List<VacancyTypeEntity>) -> Unit)
}