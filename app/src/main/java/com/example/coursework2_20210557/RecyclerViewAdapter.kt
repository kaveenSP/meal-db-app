package com.example.coursework2_20210557

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2_20210557.database.MealData
import java.util.concurrent.Executors

class RecyclerViewAdapter(private val mealsList: ArrayList<MealData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MealViewHolder>() {
    companion object {
        var mealDetails:String? = ""
    }
    private lateinit var context: Context

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.meal_image)
        val mealName: TextView = itemView.findViewById(R.id.tvMealName)
        val mealCat: TextView = itemView.findViewById(R.id.meal_category)
        var mealInfo: TextView = itemView.findViewById(R.id.meal_details_hidden)

        init {
            itemView.setOnClickListener {
                showPopup()
            }
        }

        @SuppressLint("MissingInflatedId", "InflateParams")
        private fun showPopup() {
            val popupView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_meal_info, null)
            val popupText = popupView.findViewById<TextView>(R.id.meal_popup)
            val closeButton = popupView.findViewById<Button>(R.id.close_button)

            // Set the text in the popup views
            popupText.text = mealInfo.text
            popupText.movementMethod = ScrollingMovementMethod()

            // Create the popup window
            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            // Set an animation for the popup window
            popupWindow.animationStyle = R.style.CustomPopupAnimation

            // Show the popup window
            popupWindow.showAtLocation(itemView, Gravity.CENTER, 0, 0)

            // Set an OnClickListener for the close button in the popup
            closeButton.setOnClickListener {
                // Dismiss the popup window when the close button is clicked
                popupWindow.dismiss()
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        //make image view circular
        holder.img.clipToOutline = true
        holder.img.background = ContextCompat.getDrawable(context, R.drawable.round_image)
        
        val meal = mealsList[position]

        // Declaring executor
        val executor = Executors.newSingleThreadExecutor()
        
        val handler = Handler(Looper.getMainLooper())

        // Initialize image
        var image: Bitmap?

        //Background Process
        executor.execute {
            //retrieve and set the image to text view
            try {
                val `in` = java.net.URL(meal.MealThumb).openStream()
                image = BitmapFactory.decodeStream(`in`)

                //for UI change
                handler.post {
                    holder.img.setImageBitmap(image)
                }
            }
            //if url does not point to an image
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val strBuilder = StringBuilder()
        strBuilder.append(meal.Meal)
        holder.mealName.text = strBuilder
        holder.mealCat.text = meal.Category
        mealDetails = meal.toString()
        val mealInfoString = "Meal Name : " + meal.Meal + "\n\n" + "Alternative Drinks : " + meal.DrinkAlternate + "\n\n" + "Category : " + meal.Category + "\n\n" + "Area : " + meal.Area + "\n\n" + "Instructions : " + meal.Instructions + "\n\n" + "Tags : " + meal.Tags + "\n\n" + "Youtube : " + meal.Youtube + "\n\n" + "Ingredient 01 : " + meal.Ingredient01 + "\n" + "Measurement 01 : " + meal.Measure01 + "\n\n" + "Ingredient 02 : " + meal.Ingredient02 + "\n" + "Measurement 02 : " + meal.Measure02 + "\n\n" + "Ingredient 03 : " + meal.Ingredient03 + "\n" + "Measurement 03 : " + meal.Measure03 + "\n\n" + "Ingredient 04 : " + meal.Ingredient04 + "\n" + "Measurement 04 : " + meal.Measure04 + "\n\n" + "Ingredient 05 : " + meal.Ingredient05 + "\n" + "Measurement 05 : " + meal.Measure05 + "\n\n" + "Ingredient 06 : " + meal.Ingredient06 + "\n" + "Measurement 06 : " + meal.Measure06 + "\n\n" + "Ingredient 07 : " + meal.Ingredient07 + "\n" + "Measurement 07 : " + meal.Measure07 + "\n\n" + "Ingredient 08 : " + meal.Ingredient08 + "\n" + "Measurement 08 : " + meal.Measure08 + "\n\n" + "Ingredient 09 : " + meal.Ingredient09 + "\n" + "Measurement 09 : " + meal.Measure09 + "\n\n" + "Ingredient 10 : " + meal.Ingredient10 + "\n" + "Measurement 10 : " + meal.Measure10 + "\n\n" + "Ingredient 11 : " + meal.Ingredient11 + "\n" + "Measurement 11 : " + meal.Measure11 + "\n\n" + "Ingredient 12 : " + meal.Ingredient12 + "\n" + "Measurement 12 : " + meal.Measure12 + "\n\n" + "Ingredient 13 : " + meal.Ingredient13 + "\n" + "Measurement 13 : " + meal.Measure13 + "\n\n" + "Ingredient 14 : " + meal.Ingredient14 + "\n" + "Measurement 14 : " + meal.Measure14 + "\n\n" + "Ingredient 15 : " + meal.Ingredient15 + "\n" + "Measurement 15 : " + meal.Measure15 + "\n\n" + "Ingredient 16 : " + meal.Ingredient16 + "\n" + "Measurement 16 : " + meal.Measure16 + "\n\n" + "Ingredient 17 : " + meal.Ingredient17 + "\n" + "Measurement 17 : " + meal.Measure17 + "\n\n" + "Ingredient 18 : " + meal.Ingredient18 + "\n" + "Measurement 18 : " + meal.Measure18 + "\n\n" + "Ingredient 19 : " + meal.Ingredient19 + "\n" + "Measurement 19 : " + meal.Measure19 + "\n\n" + "Ingredient 20 : " + meal.Ingredient20 + "\n" + "Measurement 20 : " + meal.Measure20 + "\n\n" + "Source : " + meal.Source
        holder.mealInfo.text = mealInfoString
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}