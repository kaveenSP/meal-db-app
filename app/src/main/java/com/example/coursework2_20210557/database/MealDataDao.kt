package com.example.coursework2_20210557.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MealDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMealData(mealData: MealData)

    @Query("SELECT * FROM MealData ORDER BY strMeal ASC")
    fun readAllMealData(): LiveData<List<MealData>>

//    @Upsert
//    fun upsertMealData(mealData: MealData)
}