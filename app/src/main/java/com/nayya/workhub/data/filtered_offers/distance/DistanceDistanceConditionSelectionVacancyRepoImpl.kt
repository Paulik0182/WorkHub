package com.nayya.workhub.data.filtered_offers.distance

import android.content.Context
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.DistanceConditionSelectionVacancyRepo

private const val DISTANCE_SHARED_PREFERENCES = "DISTANCE_SHARED_PREFERENCES"
private const val DISTANCE_FROM_CITY_KEY = "DISTANCE_FROM_CITY_KEY"

class DistanceDistanceConditionSelectionVacancyRepoImpl(
    private val context: Context
) : DistanceConditionSelectionVacancyRepo {

    override fun getConditionSelectionIds(callback: (String) -> Unit) {
        val data = getDistance()
        callback(data)
    }

    override fun setDistance(id: String) {
        val sharedPreferencesDistance =
            context.getSharedPreferences(DISTANCE_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        sharedPreferencesDistance.edit()
            .putString(
                DISTANCE_FROM_CITY_KEY,
                id
            ).apply()
    }

    override fun getDistance(): String {
        val sharedPreferencesDistance =
            context.getSharedPreferences(DISTANCE_SHARED_PREFERENCES, Context.MODE_PRIVATE)

        return sharedPreferencesDistance.getString(DISTANCE_FROM_CITY_KEY, "0") ?: "0"
    }
}