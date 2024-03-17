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
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @Expose
    @ColumnInfo(name = "level")
    @SerializedName("level")
    val level: Int?,

    @Expose
    @ColumnInfo(name = "category_name")
    @SerializedName("category_name")
    val categoryName: String,

    @Expose
    @ColumnInfo(name = "parentId")
    @SerializedName("parentId")
    val parentId: Int?

) : Parcelable
