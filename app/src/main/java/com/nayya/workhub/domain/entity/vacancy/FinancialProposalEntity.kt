package com.nayya.workhub.domain.entity.vacancy

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FinancialProposalEntity(

    @SerializedName("key")
    val key: String,

    @SerializedName("financial_proposal")
    val financialProposal: List<String>
) : Parcelable
