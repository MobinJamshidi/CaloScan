package com.mobinjam.caloscan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foods: List<FoodEntity>)

    @Query("SELECT * FROM food_table WHERE LOWER(name) LIKE '%' || LOWER(:searchQuery) || '%' LIMIT 1")
    suspend fun findFoodByName(searchQuery: String): FoodEntity?

}