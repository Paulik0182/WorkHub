package com.nayya.workhub.data

import com.nayya.workhub.domain.repo.VacanciesTypeRepo

/**
 * нужно подумать как правильно реализовать получение позиций по вакансиям.
 * нужно придусматреть предлогаемый набор позиций в Api и поиск по ключивым
 * словам, а также совместное применение - ключивые слова с предлогаемыми
 * позициями вокансий (разделы)
 */
class VacanciesTypeRepoImpl : VacanciesTypeRepo {
    override fun getVacanciesType(): List<String> = mutableListOf(
        "IT",
        "Android",
        "Java"
    )
}