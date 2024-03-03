package com.nayya.workhub.domain.entity.offer.vacansy_dto.addition

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Salary(

    @SerializedName("currency")
    val currency: Currency?,

    @SerializedName("from")
    val from: Int?,

    @SerializedName("salaryKind")
    val salaryKind: SalaryKind?,

    @SerializedName("timeUnit")
    val timeUnit: TimeUnit?,

    @SerializedName("to")
    val to: Int?

) : Parcelable