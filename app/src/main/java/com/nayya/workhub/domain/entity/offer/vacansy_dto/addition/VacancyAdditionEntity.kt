package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyAdditionEntity(

    @SerializedName("breadcrumbsReducer")
    val breadcrumbsReducer: BreadcrumbsReducer?,

    @SerializedName("offerReducer")
    val offerReducer: OfferReducer?

) : Parcelable