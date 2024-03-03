package com.nayya.workhub.domain.entity.offer.repo

import com.nayya.workhub.domain.entity.offer.vacansy_dto.VacancyHeadingEntity
import com.nayya.workhub.domain.entity.offer.vacansy_dto.addition.VacancyAdditionEntity

interface PracujPlOfferVacancyRepo {

    fun extractOfferHeading(url: String, listener: (VacancyHeadingEntity) -> Unit)
    fun extractOfferAddition(url: String, listener: (VacancyAdditionEntity) -> Unit)

}