package com.example.coursework2_20210557

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2_20210557.database.MealData
import com.example.coursework2_20210557.database.MealDataDao
import com.example.coursework2_20210557.database.MealDatabase
import com.google.android.material.textfield.TextInputEditText

class SearchMealActivity : AppCompatActivity() {
    private lateinit var mealsDao: MealDataDao
    private lateinit var mealsDatabse: MealDatabase
    private var mealsList: ArrayList<MealData> = arrayListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal)

        //room db instance
        mealsDatabse =MealDatabase.getDatabase(this)

        //reference of DAO
        mealsDao = mealsDatabse.mealDataDao()

        val searchMeal = findViewById<Button>(R.id.search_button)
        val mealInput = findViewById<TextInputEditText>(R.id.search_input)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchMeals)

        searchMeal.setOnClickListener {
            //clear
            mealsList.clear()
            if (mealInput.text.toString() == "") {
                Toast.makeText(applicationContext, "Enter Meal Name Or An Ingredient", Toast.LENGTH_LONG).show()
            } else {
                mealsDatabse.mealDataDao().searchMeals(mealInput.text.toString()).observe(this, Observer { meals ->
                    // clear the mealsList
                    mealsList.clear()
                    //add meals
                    mealsList.addAll(meals)

                    if (mealsList.size == 0) {
                        Toast.makeText(applicationContext, "No Results Found On Database", Toast.LENGTH_LONG).show()
                    } else {
                        //dynamically display data through a list view
                        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealActivity)
                        recyclerView.adapter = RecyclerViewAdapter(mealsList)
                    }
                })
            }
        }
    }

    //save instances
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("mealsList", mealsList)
    }

    //restore instances
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        mealsList = savedInstanceState.getParcelableArrayList("mealsList") ?: ArrayList()

        //recycler view to dynamically display the list of json data
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchMeals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(mealsList)

    }

}