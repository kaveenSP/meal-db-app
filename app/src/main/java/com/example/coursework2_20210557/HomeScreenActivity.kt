package com.example.coursework2_20210557
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.room.Room
import com.example.coursework2_20210557.database.MealData
import com.example.coursework2_20210557.database.MealDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var mealDB: MealDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        mealDB = MealDatabase.getDatabase(this)

        val addMeal = findViewById<Button>(R.id.add_meal)
        val searchMealIngredient = findViewById<Button>(R.id.search_meal_ingredient)
        val searchMeal = findViewById<Button>(R.id.search_meal)

        addMeal.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val meal01 = MealData(
                    "1234",
                    "Sweet and Sour Pork",
                    null,"Pork",
                    "Chinese",
                    "Preparation\r\n1. Crack the egg into a bowl. Separate the egg white and yolk.\r\n\r\nSweet and Sour Pork\r\n2. Slice the porktenderloin into ips.\r\n\r\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and anegg white.\r\n\r\n4. Marinade the pork ips for about 20 minutes.\r\n\r\n5. Put the remaining starch in a bowl. Add some water and vinegar tomake a starchy sauce.\r\n\r\nSweet and Sour Pork\r\nCooking Inuctions\r\n1. Pour the cooking oil into a wok and heat to 190\u00b0C(375\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\r\n\r\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\r\n\r\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\r\n\r\n4. Pour in thestarchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\r\n\r\n5. Serve on a plate and add somecoriander for decoration.",
                    "https://www.themealdb.com/images/media/meals/1529442316.jpg",
                    "Sweet",
                    "https://www.youtube.com/watch?v=mdaBIhgEAMo",
                    "Pork",
                    "Egg",
                    "Water",
                    "Salt",
                    "Sugar",
                    "Soy Sauce",
                    "Starch",
                    "Tomato Puree",
                    "Vinegar",
                    "Coriander",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "200g",
                    "1",
                    "Dash",
                    "1/2 tsp",
                    "1 tsp ",
                    "10g",
                    "10g",
                    "30g",
                    "10g",
                    "Dash",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                )
                mealDB.mealDataDao().insertMealData(meal01)
            }

        }
        searchMealIngredient.setOnClickListener {
            val ingredientSearchActivityIntent = Intent(this, SearchIngredientActivity::class.java)
            startActivity(ingredientSearchActivityIntent)
        }
        searchMeal.setOnClickListener {

        }
    }
}