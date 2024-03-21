package com.nayya.workhub.data.filtered_offers.location

import android.content.Context
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionRepo

private const val LOCATION_PREFERENCES_KEY = "LOCATION_PREFERENCES_KEY"

class LocationSelectionRepoImpl(
    private val context: Context
) : LocationSelectionRepo {

    override fun getLocationSelectionIds(callback: (List<String>) -> Unit) {
        val data = getLocationSelection()
        callback(data)
    }

    override fun setLocationSelection(id: String, selection: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(LOCATION_PREFERENCES_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(id, selection).apply()
    }

    private fun getLocationSelection(): List<String> {
        val sharedPreferences =
            context.getSharedPreferences(LOCATION_PREFERENCES_KEY, Context.MODE_PRIVATE)

        return sharedPreferences.all.filter {
            it.value is Boolean && it.value == true
        }.keys.toList() // все значения с true и достаем ключи выбраных
    }
}