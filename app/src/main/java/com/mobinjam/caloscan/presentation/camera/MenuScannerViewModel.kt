package com.mobinjam.caloscan.presentation.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobinjam.caloscan.domain.model.FoodItem
import com.mobinjam.caloscan.domain.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuScannerViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    // لیستی از غذاهای پیدا شده برای نمایش روی صفحه دوربین
    private val _detectedFoods = MutableStateFlow<List<FoodItem>>(emptyList())
    val detectedFoods: StateFlow<List<FoodItem>> = _detectedFoods.asStateFlow()

    // لیستی برای نگهداری غذاهای مصرف شده امروز (مورد نیاز برای داشبورد)
    private val _consumedFoods = MutableStateFlow<List<FoodItem>>(emptyList())
    val consumedFoods: StateFlow<List<FoodItem>> = _consumedFoods.asStateFlow()

    init {
        // وارد کردن دیتای اولیه کافه و رستوران به دیتابیس
        viewModelScope.launch {
            repository.initializeDummyData()
        }
    }

    // پردازش متن‌های دوربین و جستجو در دیتابیس
    fun processDetectedText(text: String) {
        viewModelScope.launch {
            val words = text.split("\n", " ")
            val foundItems = mutableListOf<FoodItem>()

            for (word in words) {
                if (word.length > 2) {
                    val food = repository.findFood(word.trim())
                    if (food != null && !foundItems.contains(food)) {
                        foundItems.add(food)
                    }
                }
            }

            if (foundItems.isNotEmpty()) {
                _detectedFoods.value = foundItems
            }
        }
    }

    // اضافه کردن غذای انتخاب شده به لیست مصرفی امروز
    fun consumeFood(food: FoodItem) {
        val currentList = _consumedFoods.value.toMutableList()
        currentList.add(food)
        _consumedFoods.value = currentList
    }

    // حذف غذا از لیست امروز
    fun removeConsumedFood(food: FoodItem) {
        val currentList = _consumedFoods.value.toMutableList()
        currentList.remove(food)
        _consumedFoods.value = currentList
    }
}