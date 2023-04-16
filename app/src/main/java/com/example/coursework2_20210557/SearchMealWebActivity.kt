package com.example.coursework2_20210557

import android.os.Bundle
import android.util.Log
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
    private lateinit var mealsDatabse: MealDatabase

    private var mealsList: ArrayList<MealData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal_web)

        //instance of the room db
        mealsDatabse = MealDatabase.getDatabase(this)

        //reference of DAO methods to interact with db
        mealsDao = mealsDatabse.mealDataDao()

//        //repo object which acts as a mediator between the viewmodel and DAO
//        MealsRepo = MealsRepo(mealsDao)

        //retrieve the id's from the layouts
        val retrieveMeals = findViewById<Button>(R.id.search_meal_web)
        val mealWebInput = findViewById<TextInputEditText>(R.id.meal_web_input)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchWebMeals)

        //retrieved array
        var mealsJS: JSONArray

        //action to perform on retrive meals button
        retrieveMeals.setOnClickListener {
            //clear the mealsList arraylist
            mealsList.clear()

            val mealTxt = mealWebInput.text.toString()

            lifecycleScope.launch {
                val response = httpGetMeal(mealTxt)
                try {
                    val mealData = JSONObject(response)
                    mealsJS = mealData.getJSONArray("meals")

                    //iterate over the array to get individual objects and assign it to an array
                    for (i in 0 until mealsJS.length()) {
                        val singlemeal = mealsJS.getJSONObject(i)
                        val meal = createMealFromJSON(singlemeal)
                        mealsList.add(meal)
                    }

                    //recycler view to dynamically display the list of json data
                    recyclerView?.layoutManager = LinearLayoutManager(this@SearchMealWebActivity)
                    recyclerView?.adapter = RecyclerViewAdapter(mealsList)

                } catch (e: JSONException) {
                    // handle error
                    Toast.makeText(applicationContext, "No Results Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //map meals json object to entity
    fun createMealFromJSON(singlemeal: JSONObject): MealData {
        return MealData(
            singlemeal.getString("idMeal"),
            singlemeal.getString("strMeal"),
            singlemeal.getString("strDrinkAlternate"),
            singlemeal.getString("strCategory"),
            singlemeal.getString("strArea"),
            singlemeal.getString("strInstructions"),
            singlemeal.getString("strMealThumb"),
            singlemeal.getString("strTags"),
            singlemeal.getString("strYoutube"),
            singlemeal.getString("strIngredient1"),
            singlemeal.getString("strIngredient2"),
            singlemeal.getString("strIngredient3"),
            singlemeal.getString("strIngredient4"),
            singlemeal.getString("strIngredient5"),
            singlemeal.getString("strIngredient6"),
            singlemeal.getString("strIngredient7"),
            singlemeal.getString("strIngredient8"),
            singlemeal.getString("strIngredient9"),
            singlemeal.getString("strIngredient10"),
            singlemeal.getString("strIngredient11"),
            singlemeal.getString("strIngredient12"),
            singlemeal.getString("strIngredient13"),
            singlemeal.getString("strIngredient14"),
            singlemeal.getString("strIngredient15"),
            singlemeal.getString("strIngredient16"),
            singlemeal.getString("strIngredient17"),
            singlemeal.getString("strIngredient18"),
            singlemeal.getString("strIngredient19"),
            singlemeal.getString("strIngredient20"),
            singlemeal.getString("strMeasure1"),
            singlemeal.getString("strMeasure2"),
            singlemeal.getString("strMeasure3"),
            singlemeal.getString("strMeasure4"),
            singlemeal.getString("strMeasure5"),
            singlemeal.getString("strMeasure6"),
            singlemeal.getString("strMeasure7"),
            singlemeal.getString("strMeasure8"),
            singlemeal.getString("strMeasure9"),
            singlemeal.getString("strMeasure10"),
            singlemeal.getString("strMeasure11"),
            singlemeal.getString("strMeasure12"),
            singlemeal.getString("strMeasure13"),
            singlemeal.getString("strMeasure14"),
            singlemeal.getString("strMeasure15"),
            singlemeal.getString("strMeasure16"),
            singlemeal.getString("strMeasure17"),
            singlemeal.getString("strMeasure18"),
            singlemeal.getString("strMeasure19"),
            singlemeal.getString("strMeasure20"),
            singlemeal.getString("strSource"),
            singlemeal.getString("strImageSource"),
            singlemeal.getString("strCreativeCommonsConfirmed"),
            singlemeal.getString("dateModified")
        )
    }

    //http GET request to retrieve data
    private suspend fun httpGetMeal(meal: String?): String? {

        return withContext(Dispatchers.IO) {

            val inputStream: InputStream
            var result: String?

            // create URL
            val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$meal")

            // create HttpURLConnection
            val conn = url.openConnection() as HttpURLConnection

            // make GET request to the given URL
            conn.connect()

            // receive response as inputStream
            inputStream = conn.inputStream

            // convert inputstream to string
            result = inputStream.bufferedReader().use { it.readText() }

            result
        }
    }

    //save instances to state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state of your activity to the outState Bundle
        outState.putParcelableArrayList("mealsWebList", mealsList)

    }

    //restore instances from state
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)

        mealsList = savedInstanceState.getParcelableArrayList("mealsWebList") ?: ArrayList()

        //recycler view to dynamically display the list of json data
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchWebMeals)
        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealWebActivity)
        recyclerView?.adapter = RecyclerViewAdapter(mealsList)

    }
}