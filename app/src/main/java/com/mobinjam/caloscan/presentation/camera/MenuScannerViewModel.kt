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

    private val _detectedFoods = MutableStateFlow<List<FoodItem>>(emptyList())
    val detectedFoods: StateFlow<List<FoodItem>> = _detectedFoods.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initializeDummyData()
        }
    }

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
}