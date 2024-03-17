package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Props(

    @SerializedName("__N_SSP")
    val __N_SSP: Boolean?,

    @SerializedName("pageProps")
    val pageProps: PageProps?

) : Parcelable