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
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2_20210557.database.MealData
import java.util.concurrent.Executors

class RecyclerViewAdapter(private val mealsList: ArrayList<MealData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    companion object {
        var mealDetails:String? = ""
    }

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.meal_image)
        val name: TextView = itemView.findViewById(R.id.tvMealName)
        val category: TextView = itemView.findViewById(R.id.meal_category)
        var mealInfo: TextView = itemView.findViewById(R.id.meal_details_hidden)


        init {
            itemView.setOnClickListener {
                showPopup()
            }
        }

        @SuppressLint("MissingInflatedId")
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



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealsList[position]

        // Declaring executor to parse the URL
        val executor = Executors.newSingleThreadExecutor()

        // Once the executor parses the URL
        // and receives the image, handler will load it
        // in the ImageView
        val handler = Handler(Looper.getMainLooper())

        // Initializing the image
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(meal.strMealThumb).openStream()
                image = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {
                    holder.img.setImageBitmap(image)
                }
            }

            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append(meal.strMeal)

        holder.name.text = stringBuilder
        holder.category.text = meal.strCategory
        mealDetails = meal.toString()
        holder.mealInfo.text = meal.toString()
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}