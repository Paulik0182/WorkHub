package com.nayya.workhub.domain.entity.offer.repo

import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.OfferListItem

interface PracujPlOffersJobRepo {

    fun extractOffers(url: String, listener: (List<OfferListItem>) -> Unit)

    fun getCities(groupId: String, callback: (List<OfferJob>) -> Unit)
}