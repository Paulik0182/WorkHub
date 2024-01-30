package com.nayya.workhub.domain.entity.filter_category

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category_vacancies")
@Parcelize
data class CategoryVacanciesEntity(

    @Expose
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,

    @Expose
    @SerializedName("category_name")
    @ColumnInfo(name = "category_name")
    val categoryName: String,

    ) : Parcelable
