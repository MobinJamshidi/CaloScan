package com.mobinjam.caloscan.domain.model

data class FoodItem(
    val id: Int,
    val name: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float
)