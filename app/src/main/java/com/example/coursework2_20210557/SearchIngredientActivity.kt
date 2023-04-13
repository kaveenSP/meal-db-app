package com.example.coursework2_20210557


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2_20210557.database.MealData
import com.example.coursework2_20210557.database.MealDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class SearchIngredientActivity : AppCompatActivity() {

    private lateinit var mealDB: MealDatabase

    @SuppressLint("MissingInflatedId")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_ingredient)

        mealDB = MealDatabase.getDatabase(this)

        val retrieveMeals = findViewById<Button>(R.id.retrieve_meals)
        val saveMeals = findViewById<Button>(R.id.save_meals)
        val mealDetails = findViewById<TextView>(R.id.meal_details)
        val ingredientInput = findViewById<TextInputEditText>(R.id.ingredient_input)
        var response: JSONArray? = null

        retrieveMeals.setOnClickListener {
            val ingredient = ingredientInput.text.toString()
            GlobalScope.launch {
                response = searchMeal(ingredient)


                var mealDetailString = ""
                // Update the UI on the main thread
                withContext(Dispatchers.Main) {
                    if (response != null) {
                        for (i in 0 until response!!.length()) {
                            val meal = response!!.getJSONObject(i)
                            val keys = meal.keys()
                            while (keys.hasNext()) {
                                val key = keys.next()
                                val value = meal.get(key)
                                mealDetailString += "\n$key: $value"
                            }
                            mealDetailString += "\n\n\n"
                        }
                    }
                    mealDetails.text = mealDetailString
                    mealDetails.movementMethod = ScrollingMovementMethod()
                }
            }
        }

        saveMeals.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    if (response != null) {
                        for (i in 0 until response!!.length()) {
                            val meal = response!!.getJSONObject(i)
                            val newMeal = MealData(
                                meal.getString("idMeal"),
                                meal.getString("strMeal"),
                                meal.getString("strDrinkAlternate"),
                                meal.getString("strCategory"),
                                meal.getString("strArea"),
                                meal.getString("strInstructions"),
                                meal.getString("strMealThumb"),
                                meal.getString("strTags"),
                                meal.getString("strYoutube"),
                                meal.getString("strIngredient1"),
                                meal.getString("strIngredient2"),
                                meal.getString("strIngredient3"),
                                meal.getString("strIngredient4"),
                                meal.getString("strIngredient5"),
                                meal.getString("strIngredient6"),
                                meal.getString("strIngredient7"),
                                meal.getString("strIngredient8"),
                                meal.getString("strIngredient9"),
                                meal.getString("strIngredient10"),
                                meal.getString("strIngredient11"),
                                meal.getString("strIngredient12"),
                                meal.getString("strIngredient13"),
                                meal.getString("strIngredient14"),
                                meal.getString("strIngredient15"),
                                meal.getString("strIngredient16"),
                                meal.getString("strIngredient17"),
                                meal.getString("strIngredient18"),
                                meal.getString("strIngredient19"),
                                meal.getString("strIngredient20"),
                                meal.getString("strMeasure1"),
                                meal.getString("strMeasure2"),
                                meal.getString("strMeasure3"),
                                meal.getString("strMeasure4"),
                                meal.getString("strMeasure5"),
                                meal.getString("strMeasure6"),
                                meal.getString("strMeasure7"),
                                meal.getString("strMeasure8"),
                                meal.getString("strMeasure9"),
                                meal.getString("strMeasure10"),
                                meal.getString("strMeasure11"),
                                meal.getString("strMeasure12"),
                                meal.getString("strMeasure13"),
                                meal.getString("strMeasure14"),
                                meal.getString("strMeasure15"),
                                meal.getString("strMeasure16"),
                                meal.getString("strMeasure17"),
                                meal.getString("strMeasure18"),
                                meal.getString("strMeasure19"),
                                meal.getString("strMeasure20"),
                                meal.getString("strSource"),
                                meal.getString("strImageSource"),
                                meal.getString("strCreativeCommonsConfirmed"),
                                meal.getString("dateModified")
                            )
                            mealDB.mealDataDao().insertMealData(newMeal)
                        }
                    }
            }
        }
    }
// List<MealIngredientResponse>?
    private suspend fun searchMeal(ingredient: String): JSONArray {
        var returnArray = JSONArray()
        // Build the API URL with the user input
        val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?i=$ingredient")

        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuffer()
            var inputLine: String?
            while (inputStream.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            inputStream.close()

            val stringResponse = response.toString()
            try {
                val jsonObject = JSONObject(stringResponse)
                val mealsArray = jsonObject.getJSONArray("meals")
                for (i in 0 until mealsArray.length()) {
                    val meal = mealsArray.getJSONObject(i)
                    val idMeal = meal.getString("idMeal")
                    val mealDataUrl = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$idMeal")

                    val con = mealDataUrl.openConnection() as HttpURLConnection
                    con.requestMethod = "GET"
                    val responseCode1 = connection.responseCode
                    if (responseCode1 == HttpURLConnection.HTTP_OK) {
                        val inputStream1 = BufferedReader(InputStreamReader(con.inputStream))
                        val response1 = StringBuffer()
                        var inputLine1: String?
                        while (inputStream1.readLine().also { inputLine1 = it } != null) {
                            response1.append(inputLine1)
                        }
                        inputStream1.close()
                        val mealObjectJSON = JSONObject(response1.toString())
                        returnArray.put(mealObjectJSON.getJSONArray("meals").getJSONObject(0))


                    } else {
                        println("GET request not successful")
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            println("GET request not successful")
        }
    return returnArray
    }
}