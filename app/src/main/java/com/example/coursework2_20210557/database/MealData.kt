package com.example.coursework2_20210557.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealData")
data class MealData(
    @PrimaryKey(autoGenerate = false) val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strTags: String?,
    val strYoutube: String?,
    val strIngredient01: String?,
    val strIngredient02: String?,
    val strIngredient03: String?,
    val strIngredient04: String?,
    val strIngredient05: String?,
    val strIngredient06: String?,
    val strIngredient07: String?,
    val strIngredient08: String?,
    val strIngredient09: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    val strMeasure01: String?,
    val strMeasure02: String?,
    val strMeasure03: String?,
    val strMeasure04: String?,
    val strMeasure05: String?,
    val strMeasure06: String?,
    val strMeasure07: String?,
    val strMeasure08: String?,
    val strMeasure09: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?,
    val strSource: String?,
    val strImageSource: String?,
    val strCreativeCommonsConfirmed: String?,
    val strDateModified: String?,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMeal)
        parcel.writeString(strMeal)
        parcel.writeString(strDrinkAlternate)
        parcel.writeString(strCategory)
        parcel.writeString(strArea)
        parcel.writeString(strInstructions)
        parcel.writeString(strMealThumb)
        parcel.writeString(strTags)
        parcel.writeString(strYoutube)
        parcel.writeString(strIngredient01)
        parcel.writeString(strIngredient02)
        parcel.writeString(strIngredient03)
        parcel.writeString(strIngredient04)
        parcel.writeString(strIngredient05)
        parcel.writeString(strIngredient06)
        parcel.writeString(strIngredient07)
        parcel.writeString(strIngredient08)
        parcel.writeString(strIngredient09)
        parcel.writeString(strIngredient10)
        parcel.writeString(strIngredient11)
        parcel.writeString(strIngredient12)
        parcel.writeString(strIngredient13)
        parcel.writeString(strIngredient14)
        parcel.writeString(strIngredient15)
        parcel.writeString(strIngredient16)
        parcel.writeString(strIngredient17)
        parcel.writeString(strIngredient18)
        parcel.writeString(strIngredient19)
        parcel.writeString(strIngredient10)
        parcel.writeString(strMeasure01)
        parcel.writeString(strMeasure02)
        parcel.writeString(strMeasure03)
        parcel.writeString(strMeasure04)
        parcel.writeString(strMeasure05)
        parcel.writeString(strMeasure06)
        parcel.writeString(strMeasure07)
        parcel.writeString(strMeasure08)
        parcel.writeString(strMeasure09)
        parcel.writeString(strMeasure10)
        parcel.writeString(strMeasure11)
        parcel.writeString(strMeasure12)
        parcel.writeString(strMeasure13)
        parcel.writeString(strMeasure14)
        parcel.writeString(strMeasure15)
        parcel.writeString(strMeasure16)
        parcel.writeString(strMeasure17)
        parcel.writeString(strMeasure18)
        parcel.writeString(strMeasure19)
        parcel.writeString(strMeasure20)
        parcel.writeString(strSource)
        parcel.writeString(strImageSource)
        parcel.writeString(strCreativeCommonsConfirmed)
        parcel.writeString(strDateModified)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MealData> {
        override fun createFromParcel(parcel: Parcel): MealData {
            return MealData(parcel)
        }

        override fun newArray(size: Int): Array<MealData?> {
            return arrayOfNulls(size)
        }
    }
}

