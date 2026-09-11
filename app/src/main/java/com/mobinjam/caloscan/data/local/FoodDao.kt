package com.mobinjam.caloscan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foods: List<FoodEntity>)

    // تغییر مهم: جستجوی دقیق فقط برای همان کلمه
    @Query("SELECT * FROM food_table WHERE LOWER(name) = LOWER(:searchQuery) LIMIT 1")
    suspend fun findFoodByName(searchQuery: String): FoodEntity?

}