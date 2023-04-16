package com.example.coursework2_20210557


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
    private var mealDetailString = ""

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
            mealDetailString = ""
            val ingredient = ingredientInput.text.toString()
            if (ingredient == "") {
                Toast.makeText(applicationContext, "Enter An Ingredient", Toast.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch {
                    response = searchMeal(ingredient)

                    // Update the UI on the main thread
                    withContext(Dispatchers.Main) {
                        if (response != null) {
                            for (i in 0 until response!!.length()) {
                                val meal = response!!.getJSONObject(i)
                                val keys = meal.keys()
                                while (keys.hasNext()) {
                                    val key = keys.next()
                                    val value = meal.get(key)
                                    if (value != null || value != "") {
                                        mealDetailString += "\n$key: $value"
                                    }
                                }
                                mealDetailString += "\n\n\n"
                            }
                        }
                        mealDetails.text = mealDetailString
                        mealDetails.movementMethod = ScrollingMovementMethod()
                    }
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

    private fun searchMeal(ingredient: String): JSONArray {
        var returnArray = JSONArray()
        // Build the API URL with the user input
        val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$ingredient")

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
                returnArray = jsonObject.getJSONArray("meals")
            } catch (e: JSONException) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(applicationContext, "No Results Found", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

        }

    return returnArray
    }

    //save instances to state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state of your activity to the outState Bundle
        outState.putString("mealData", mealDetailString)

    }

    //restore instances from state
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)

        mealDetailString = savedInstanceState.getString("mealData") ?: String()

        //recycler view to dynamically display the list of json data
        val mealDetails = findViewById<TextView>(R.id.meal_details)
        mealDetails.text = mealDetailString

    }
}