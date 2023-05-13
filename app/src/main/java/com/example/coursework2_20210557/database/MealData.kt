package com.example.coursework2_20210557.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealData")
data class MealData(
    @PrimaryKey(autoGenerate = false) val idMeal: String,
    val Meal: String,
    val DrinkAlternate: String?,
    val Category: String?,
    val Area: String?,
    val Instructions: String?,
    val MealThumb: String?,
    val Tags: String?,
    val Youtube: String?,
    val Ingredient01: String?,
    val Ingredient02: String?,
    val Ingredient03: String?,
    val Ingredient04: String?,
    val Ingredient05: String?,
    val Ingredient06: String?,
    val Ingredient07: String?,
    val Ingredient08: String?,
    val Ingredient09: String?,
    val Ingredient10: String?,
    val Ingredient11: String?,
    val Ingredient12: String?,
    val Ingredient13: String?,
    val Ingredient14: String?,
    val Ingredient15: String?,
    val Ingredient16: String?,
    val Ingredient17: String?,
    val Ingredient18: String?,
    val Ingredient19: String?,
    val Ingredient20: String?,
    val Measure01: String?,
    val Measure02: String?,
    val Measure03: String?,
    val Measure04: String?,
    val Measure05: String?,
    val Measure06: String?,
    val Measure07: String?,
    val Measure08: String?,
    val Measure09: String?,
    val Measure10: String?,
    val Measure11: String?,
    val Measure12: String?,
    val Measure13: String?,
    val Measure14: String?,
    val Measure15: String?,
    val Measure16: String?,
    val Measure17: String?,
    val Measure18: String?,
    val Measure19: String?,
    val Measure20: String?,
    val Source: String?,
    val ImageSource: String?,
    val CreativeCommonsConfirmed: String?,
    val DateModified: String?,
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
        parcel.writeString(Meal)
        parcel.writeString(DrinkAlternate)
        parcel.writeString(Category)
        parcel.writeString(Area)
        parcel.writeString(Instructions)
        parcel.writeString(MealThumb)
        parcel.writeString(Tags)
        parcel.writeString(Youtube)
        parcel.writeString(Ingredient01)
        parcel.writeString(Ingredient02)
        parcel.writeString(Ingredient03)
        parcel.writeString(Ingredient04)
        parcel.writeString(Ingredient05)
        parcel.writeString(Ingredient06)
        parcel.writeString(Ingredient07)
        parcel.writeString(Ingredient08)
        parcel.writeString(Ingredient09)
        parcel.writeString(Ingredient10)
        parcel.writeString(Ingredient11)
        parcel.writeString(Ingredient12)
        parcel.writeString(Ingredient13)
        parcel.writeString(Ingredient14)
        parcel.writeString(Ingredient15)
        parcel.writeString(Ingredient16)
        parcel.writeString(Ingredient17)
        parcel.writeString(Ingredient18)
        parcel.writeString(Ingredient19)
        parcel.writeString(Ingredient10)
        parcel.writeString(Measure01)
        parcel.writeString(Measure02)
        parcel.writeString(Measure03)
        parcel.writeString(Measure04)
        parcel.writeString(Measure05)
        parcel.writeString(Measure06)
        parcel.writeString(Measure07)
        parcel.writeString(Measure08)
        parcel.writeString(Measure09)
        parcel.writeString(Measure10)
        parcel.writeString(Measure11)
        parcel.writeString(Measure12)
        parcel.writeString(Measure13)
        parcel.writeString(Measure14)
        parcel.writeString(Measure15)
        parcel.writeString(Measure16)
        parcel.writeString(Measure17)
        parcel.writeString(Measure18)
        parcel.writeString(Measure19)
        parcel.writeString(Measure20)
        parcel.writeString(Source)
        parcel.writeString(ImageSource)
        parcel.writeString(CreativeCommonsConfirmed)
        parcel.writeString(DateModified)
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

