package com.nayya.workhub.domain.repo

interface CategorySelectionRepo {

    fun getCategorySelectionIds(callback: (List<String>) -> Unit)
}