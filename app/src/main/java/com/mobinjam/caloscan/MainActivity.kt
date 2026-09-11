package com.mobinjam.caloscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobinjam.caloscan.domain.model.FoodItem // اضافه شدن ایمپورت کلاس غذا
import com.mobinjam.caloscan.presentation.camera.MenuScannerScreen
import com.mobinjam.caloscan.presentation.camera.MenuScannerViewModel
import com.mobinjam.caloscan.presentation.dashboard.DashboardScreen
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ساخت کنترل‌کننده مسیرها
            val navController = rememberNavController()

            // استفاده از یک ViewModel مشترک
            val sharedViewModel: MenuScannerViewModel = koinViewModel()

            // تنظیم داشبورد به عنوان صفحه اول (startDestination)
            NavHost(navController = navController, startDestination = "dashboard") {
                composable("dashboard") {
                    val consumedFoods by sharedViewModel.consumedFoods.collectAsState<List<FoodItem>>()

                    DashboardScreen(
                        consumedFoods = consumedFoods,
                        onNavigateToCamera = { navController.navigate("camera") },
                        onDeleteFood = { food -> sharedViewModel.removeConsumedFood(food) } // پاس دادن تابع حذف
                    )
                }

                composable("camera") {
                    MenuScannerScreen(
                        viewModel = sharedViewModel,
                        onFoodSelected = { navController.popBackStack() },
                        onBackPressed = { navController.popBackStack() } // پاس دادن تابع برگشت
                    )
                }
            }
        }
    }
}