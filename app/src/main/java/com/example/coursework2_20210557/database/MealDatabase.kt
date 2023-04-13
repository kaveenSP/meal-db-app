package com.example.coursework2_20210557.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MealData::class], version = 1, exportSchema = false)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDataDao() : MealDataDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "Meals"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}