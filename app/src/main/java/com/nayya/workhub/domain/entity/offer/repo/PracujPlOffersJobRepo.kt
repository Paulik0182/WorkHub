package com.nayya.workhub.domain.entity.offer.repo

import com.nayya.workhub.domain.entity.offer.OfferListItem

interface PracujPlOffersJobRepo {

    fun extractOffers(url: String, listener: (List<OfferListItem>) -> Unit)
}