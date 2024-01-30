package com.nayya.workhub.data

import com.nayya.workhub.domain.repo.CategorySelectionRepo

class CategorySelectionRepoImpl : CategorySelectionRepo {

    override fun getCategorySelectionIds(callback: (List<String>) -> Unit) {
        val fakeData = getSelection()
        callback(fakeData)
    }

    private fun getSelection(): List<String> {
        return listOf(
            "1",
            "5"
        )
    }
}