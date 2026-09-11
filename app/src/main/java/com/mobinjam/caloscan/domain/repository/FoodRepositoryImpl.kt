package com.mobinjam.caloscan.data.repository

import com.mobinjam.caloscan.data.local.FoodDao
import com.mobinjam.caloscan.data.local.FoodEntity
import com.mobinjam.caloscan.data.local.toFoodItem
import com.mobinjam.caloscan.domain.model.FoodItem
import com.mobinjam.caloscan.domain.repository.FoodRepository

class FoodRepositoryImpl(
    private val dao: FoodDao
) : FoodRepository {

    override suspend fun findFood(name: String): FoodItem? {
        return dao.findFoodByName(name)?.toFoodItem()
    }

    override suspend fun initializeDummyData() {
        val dummyFoods = listOf(
            FoodEntity(name = "Pizza", calories = 266, protein = 11.4f, carbs = 33.0f, fat = 10.0f),
            FoodEntity(name = "Burger", calories = 295, protein = 14.0f, carbs = 24.0f, fat = 14.0f),
            FoodEntity(name = "Salad", calories = 152, protein = 5.0f, carbs = 10.0f, fat = 11.0f),
            FoodEntity(name = "Pasta", calories = 131, protein = 5.0f, carbs = 25.0f, fat = 1.1f),
            FoodEntity(name = "Steak", calories = 271, protein = 25.0f, carbs = 0.0f, fat = 19.0f),
            FoodEntity(name = "Coffee", calories = 2, protein = 0.3f, carbs = 0.0f, fat = 0.0f)
        )
        dao.insertFoods(dummyFoods)
    }
}