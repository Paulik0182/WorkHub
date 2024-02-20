package com.nayya.workhub.domain.entity.favorite

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteEntity2(

    @field:PrimaryKey
    @SerializedName("key")
    val key: String,

    @SerializedName("offer_id")
    @ColumnInfo(name = "offer_id")
    val offerId: String,

    @SerializedName("is_favorite")
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false

) : Parcelable
