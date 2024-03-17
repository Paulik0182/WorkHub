package com.nayya.workhub.data.Room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nayya.workhub.App
import com.nayya.workhub.domain.dto.CategoryVacanciesDao
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

@Database(
    entities = [
        CategoryVacanciesEntity::class
    ],
    version = 1, exportSchema = false
)

abstract class CategoryVacanciesRoomDb : RoomDatabase() {

    abstract fun categoryVacanciesDao(): CategoryVacanciesDao

    companion object {
        private const val DB_NAME = "database.db"
        val instanceRoom by lazy {
            Room.databaseBuilder(App.instance, CategoryVacanciesRoomDb::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}