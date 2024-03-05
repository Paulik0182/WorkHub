package com.nayya.workhub.domain.entity.filter_category

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "distance_in_km_entity")
@Parcelize
data class DistanceInKmEntity(

    @Expose
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,

    @Expose
    @SerializedName("distanceFromCityInKm")
    @ColumnInfo(name = "distanceFromCityInKm")
    val distanceFromCityInKm: Int,

    ) : Parcelable
