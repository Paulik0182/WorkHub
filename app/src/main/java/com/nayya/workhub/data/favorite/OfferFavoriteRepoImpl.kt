package com.nayya.workhub.data.favorite

import com.nayya.workhub.domain.repo.OfferFavoriteRepo

class OfferFavoriteRepoImpl : OfferFavoriteRepo {

    private val dataFavorite: MutableSet<String> = HashSet()
    override fun setFavorite(offerId: String) {
        dataFavorite.add(offerId)
    }

    override fun removeFromFavorite(offerId: String) {
        dataFavorite.remove(offerId)
    }

    override fun setFavorite(offerId: String, isFavorite: Boolean) {
        if (isFavorite) {
            setFavorite(offerId)
        } else {
            removeFromFavorite(offerId)
        }
    }

    override fun getFavoriteEntities(): List<String> {
        return ArrayList(dataFavorite)
    }

    override fun isFavorite(offerId: String): Boolean {
        return dataFavorite.contains(offerId)
    }
}