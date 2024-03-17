package com.nayya.workhub.domain.repo

interface OfferFavoriteRepo {

    fun setFavorite(offerId: String)
    fun removeFromFavorite(offerId: String)
    fun setFavorite(offerId: String, isFavorite: Boolean)
    fun getFavoriteEntities(): List<String>
    fun isFavorite(offerId: String): Boolean
}