package com.mobinjam.caloscan.domain.repository

import com.mobinjam.caloscan.domain.model.FoodItem

interface FoodRepository {
    suspend fun findFood(name: String): FoodItem?

    suspend fun initializeDummyData()
}