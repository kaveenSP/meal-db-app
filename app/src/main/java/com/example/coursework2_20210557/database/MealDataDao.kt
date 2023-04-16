package com.example.coursework2_20210557.database

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface MealDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMealData(mealData: MealData)

    @Query("SELECT * FROM MealData ORDER BY strMeal ASC")
    fun readAllMealData(): LiveData<List<MealData>>

    @Query("SELECT * FROM MealData WHERE LOWER(strMeal) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient01) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient02) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient03) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient04) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient05) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient06) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient07) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient08) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient09) LIKE '%' || LOWER(:searchString) || '%' OR LOWER(strIngredient10) LIKE '%' || LOWER(:searchString) || '%'")
    fun searchMeals(searchString: String):LiveData<List<MealData>>


//    @Upsert
//    fun upsertMealData(mealData: MealData)
}