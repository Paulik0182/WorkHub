package com.nayya.workhub.data

import android.content.Context
import com.nayya.workhub.domain.repo.CategorySelectionRepo

private const val CATEGORY_VACANCIES_PREFERENCES_KEY = "CATEGORY_VACANCIES_PREFERENCES_KEY"

class CategorySelectionRepoImpl(
    private val context: Context
) : CategorySelectionRepo {

    override fun getCategorySelectionIds(callback: (List<String>) -> Unit) {
        val data = getSelection()
        callback(data)
    }

    override fun setSelection(id: String, selection: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(CATEGORY_VACANCIES_PREFERENCES_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(id, selection).apply()
    }

    private fun getSelection(): List<String> {
        val sharedPreferences =
            context.getSharedPreferences(CATEGORY_VACANCIES_PREFERENCES_KEY, Context.MODE_PRIVATE)

        return sharedPreferences.all.filter {
            it.value is Boolean && it.value == true
        }.keys.toList() // все значения с true и достаем ключи выбраных
    }
}