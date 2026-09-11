package com.mobinjam.caloscan.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobinjam.caloscan.domain.model.FoodItem

@Entity(tableName = "food_table")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float
)

fun FoodEntity.toFoodItem(): FoodItem {
    return FoodItem(
        id = id,
        name = name,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fat = fat
    )
}