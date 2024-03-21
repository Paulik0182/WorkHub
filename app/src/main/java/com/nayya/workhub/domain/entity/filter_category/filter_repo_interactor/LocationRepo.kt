package com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor

import com.nayya.workhub.domain.entity.filter_category.LocationEntity

interface LocationRepo {

    fun getLocation(callback: (List<LocationEntity>) -> Unit)
}