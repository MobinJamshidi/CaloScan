package com.mobinjam.caloscan.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobinjam.caloscan.data.local.FoodDao
import com.mobinjam.caloscan.data.local.FoodEntity
import com.mobinjam.caloscan.domain.model.FoodItem
import com.mobinjam.caloscan.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodRepositoryImpl(
    private val dao: FoodDao,
    private val appContext: Context
) : FoodRepository {

    override suspend fun initializeDummyData() {
        withContext(Dispatchers.IO) {
            try {
                val jsonString = appContext.assets.open("foods.json").bufferedReader().use { it.readText() }
                val listType = object : TypeToken<List<FoodEntity>>() {}.type
                val foods: List<FoodEntity> = Gson().fromJson(jsonString, listType)
                dao.insertFoods(foods)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun findFood(name: String): FoodItem? {
        return dao.findFoodByName(name)?.let { entity ->
            FoodItem(
                id = entity.id,
                name = entity.name,
                calories = entity.calories,
                protein = entity.protein,
                carbs = entity.carbs,
                fat = entity.fat
            )
        }
    }
}