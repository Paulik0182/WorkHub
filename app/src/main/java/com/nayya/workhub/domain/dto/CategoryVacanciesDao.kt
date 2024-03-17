package com.nayya.workhub.domain.dto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

@Dao
interface CategoryVacanciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategoryVacancies(project: CategoryVacanciesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategoryVacancies(projects: List<CategoryVacanciesEntity>)

    @Query("SELECT * FROM category_vacancies") // запрос всех проектов. projects - это название таблицы
    fun getAllCategoryVacancies(): List<CategoryVacanciesEntity>

    @Update
    fun updateCategoryVacancies(copy: CategoryVacanciesEntity)

    @Delete
    fun remove(categoryVacanciesEntity: CategoryVacanciesEntity)
}