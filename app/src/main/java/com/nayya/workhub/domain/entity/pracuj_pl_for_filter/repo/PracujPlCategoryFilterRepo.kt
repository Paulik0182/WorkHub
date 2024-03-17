package com.nayya.workhub.domain.entity.pracuj_pl_for_filter.repo

import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.CategoryX

interface PracujPlCategoryFilterRepo {

    fun setCategoryFilter(url: String, listener: (List<CategoryX>) -> Unit)
}