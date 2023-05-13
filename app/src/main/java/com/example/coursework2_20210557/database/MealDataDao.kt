package com.example.coursework2_20210557.database

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface MealDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMealData(mealData: MealData)

    @Query("SELECT * FROM MealData ORDER BY Meal ASC")
    fun readAllMealData(): LiveData<List<MealData>>

    @Query("SELECT * FROM MealData WHERE LOWER(Meal) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient01) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient02) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient03) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient04) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient05) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient06) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient07) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient08) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient09) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient10) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient11) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient12) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient13) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient14) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient15) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient16) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient17) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient18) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient19) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(Ingredient20) LIKE '%' || LOWER(:searchString) || '%'")
    fun searchMeals(searchString: String):LiveData<List<MealData>>


//    @Upsert
//    fun upsertMealData(mealData: MealData)
}