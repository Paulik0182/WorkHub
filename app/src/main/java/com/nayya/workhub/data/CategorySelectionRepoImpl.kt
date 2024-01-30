package com.nayya.workhub.data

import com.nayya.workhub.domain.repo.CategorySelectionRepo

class CategorySelectionRepoImpl : CategorySelectionRepo {

    private val selectionSet = HashSet<String>()

    override fun getCategorySelectionIds(callback: (List<String>) -> Unit) {
        val fakeData = getSelection()
        callback(fakeData)
    }

    override fun setSelection(id: String, selection: Boolean) {
        if (selection) {
            selectionSet.add(id)
        } else {
            selectionSet.remove(id)
        }
    }

    private fun getSelection(): List<String> {
        return selectionSet.toList()
    }
}