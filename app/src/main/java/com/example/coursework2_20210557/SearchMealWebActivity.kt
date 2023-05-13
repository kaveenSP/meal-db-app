package com.example.coursework2_20210557

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2_20210557.database.MealData
import com.example.coursework2_20210557.database.MealDataDao
import com.example.coursework2_20210557.database.MealDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class SearchMealWebActivity : AppCompatActivity() {

    //DB instances and configs
    private lateinit var mealsDao: MealDataDao
    private lateinit var mealsDatabase: MealDatabase

    private var mealsList: ArrayList<MealData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal_web)

        //room db instance
        mealsDatabase = MealDatabase.getDatabase(this)

        //reference of DAO methods
        mealsDao = mealsDatabase.mealDataDao()

        val retrieveMeals = findViewById<Button>(R.id.search_meal_web)
        val mealWebInput = findViewById<TextInputEditText>(R.id.meal_web_input)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchWebMeals)

        //retrieved array
        var mealsInJson: JSONArray

        retrieveMeals.setOnClickListener {
            //clear the mealsList arraylist
            mealsList.clear()
            val mealTxt = mealWebInput.text.toString()
            if (mealTxt == "") {
                Toast.makeText(applicationContext, "Enter The Meal Name", Toast.LENGTH_LONG).show()
            } else {
                lifecycleScope.launch {
                    val response = httpGetMeal(mealTxt)
                    try {
                        val mealData = JSONObject(response!!)
                        mealsInJson = mealData.getJSONArray("meals")

                        //iterate over the array to get individual objects and assign it to an array
                        for (i in 0 until mealsInJson.length()) {
                            val jsonMeal = mealsInJson.getJSONObject(i)
                            val meal = jsonToMeal(jsonMeal)
                            mealsList.add(meal)
                        }

                        //dynamically display data through a recycler view
                        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealWebActivity)
                        recyclerView.adapter = RecyclerViewAdapter(mealsList)

                    } catch (e: JSONException) {
                        // handle error with a toast message
                        Toast.makeText(applicationContext, "No Results Found", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    //map json meal data -> entity
    fun jsonToMeal(jsonMeal: JSONObject): MealData {
        return MealData(
            jsonMeal.getString("idMeal"),
            jsonMeal.getString("strMeal"),
            jsonMeal.getString("strDrinkAlternate"),
            jsonMeal.getString("strCategory"),
            jsonMeal.getString("strArea"),
            jsonMeal.getString("strInstructions"),
            jsonMeal.getString("strMealThumb"),
            jsonMeal.getString("strTags"),
            jsonMeal.getString("strYoutube"),
            jsonMeal.getString("strIngredient1"),
            jsonMeal.getString("strIngredient2"),
            jsonMeal.getString("strIngredient3"),
            jsonMeal.getString("strIngredient4"),
            jsonMeal.getString("strIngredient5"),
            jsonMeal.getString("strIngredient6"),
            jsonMeal.getString("strIngredient7"),
            jsonMeal.getString("strIngredient8"),
            jsonMeal.getString("strIngredient9"),
            jsonMeal.getString("strIngredient10"),
            jsonMeal.getString("strIngredient11"),
            jsonMeal.getString("strIngredient12"),
            jsonMeal.getString("strIngredient13"),
            jsonMeal.getString("strIngredient14"),
            jsonMeal.getString("strIngredient15"),
            jsonMeal.getString("strIngredient16"),
            jsonMeal.getString("strIngredient17"),
            jsonMeal.getString("strIngredient18"),
            jsonMeal.getString("strIngredient19"),
            jsonMeal.getString("strIngredient20"),
            jsonMeal.getString("strMeasure1"),
            jsonMeal.getString("strMeasure2"),
            jsonMeal.getString("strMeasure3"),
            jsonMeal.getString("strMeasure4"),
            jsonMeal.getString("strMeasure5"),
            jsonMeal.getString("strMeasure6"),
            jsonMeal.getString("strMeasure7"),
            jsonMeal.getString("strMeasure8"),
            jsonMeal.getString("strMeasure9"),
            jsonMeal.getString("strMeasure10"),
            jsonMeal.getString("strMeasure11"),
            jsonMeal.getString("strMeasure12"),
            jsonMeal.getString("strMeasure13"),
            jsonMeal.getString("strMeasure14"),
            jsonMeal.getString("strMeasure15"),
            jsonMeal.getString("strMeasure16"),
            jsonMeal.getString("strMeasure17"),
            jsonMeal.getString("strMeasure18"),
            jsonMeal.getString("strMeasure19"),
            jsonMeal.getString("strMeasure20"),
            jsonMeal.getString("strSource"),
            jsonMeal.getString("strImageSource"),
            jsonMeal.getString("strCreativeCommonsConfirmed"),
            jsonMeal.getString("dateModified")
        )
    }

    //retrieve meal data
    private suspend fun httpGetMeal(meal: String?): String? {
        return withContext(Dispatchers.IO) {
            val inputStream: InputStream
            var result: String?
            // create URL
            val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$meal")

            // create connection
            val conn = url.openConnection() as HttpURLConnection
            conn.connect()

            //response
            inputStream = conn.inputStream

            //inputstream -> string
            result = inputStream.bufferedReader().use { it.readText() }
            result
        }
    }

    //save instances
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state of your activity to the outState Bundle
        outState.putParcelableArrayList("mealsWebList", mealsList)
    }

    //restore instances
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        mealsList = savedInstanceState.getParcelableArrayList("mealsWebList") ?: ArrayList()

        //dynamically display data through a recycler view
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchWebMeals)
        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealWebActivity)
        recyclerView.adapter = RecyclerViewAdapter(mealsList)
    }
}