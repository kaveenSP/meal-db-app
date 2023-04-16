package com.example.coursework2_20210557

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2_20210557.database.MealData
import com.example.coursework2_20210557.database.MealDataDao
import com.example.coursework2_20210557.database.MealDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMealActivity : AppCompatActivity() {
    private lateinit var mealsDao: MealDataDao
    private lateinit var mealsDatabse: MealDatabase
    private var mealsList: ArrayList<MealData> = arrayListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal)

        //instance of the room db
        mealsDatabse =MealDatabase.getDatabase(this)

        //reference of DAO methods to interact with db
        mealsDao = mealsDatabse.mealDataDao()

        //repo object which acts as a mediator between the viewmodel and DAO
//        MealsRepo = MealsRepo(mealsDao)

        //retrieve the id's from the layouts
        val searchMeal = findViewById<Button>(R.id.search_button)
        val mealInput = findViewById<TextInputEditText>(R.id.search_input)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchMeals)

        //action to perform on search meals button
        searchMeal.setOnClickListener {
            //clear the mealsList arraylist
            mealsList.clear()

            mealsDatabse.mealDataDao().searchMeals(mealInput.text.toString()).observe(this, Observer { meals ->
                mealsList.clear() // clear the mealsList before adding new data
                mealsList.addAll(meals) // add the retrieved meals to the mealsList

                if (mealsList.size == 0) {
                    Toast.makeText(applicationContext, "No Results Found On Database", Toast.LENGTH_SHORT).show()
                } else {
                    //recycler view to dynamically display the list of json data
                    recyclerView?.layoutManager = LinearLayoutManager(this@SearchMealActivity)
                    recyclerView?.adapter = RecyclerViewAdapter(mealsList)
                }
            })
        }
    }



    //save instances to state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state of your activity to the outState Bundle
        outState.putParcelableArrayList("mealsList", mealsList)
    }

    //restore instances from state
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        mealsList = savedInstanceState.getParcelableArrayList("mealsList") ?: ArrayList()

        //recycler view to dynamically display the list of json data
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSearchMeals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = RecyclerViewAdapter(mealsList)

    }

}