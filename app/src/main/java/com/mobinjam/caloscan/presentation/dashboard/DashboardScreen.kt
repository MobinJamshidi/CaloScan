package com.mobinjam.caloscan.presentation.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobinjam.caloscan.domain.model.FoodItem

@Composable
fun DashboardScreen(
    consumedFoods: List<FoodItem>,
    onNavigateToCamera: () -> Unit,
    onDeleteFood: (FoodItem) -> Unit // تابع حذف اضافه شد
) {
    val totalCalories = consumedFoods.sumOf { it.calories }
    val totalProtein = consumedFoods.map { it.protein }.sum()
    val totalCarbs = consumedFoods.map { it.carbs }.sum()
    val totalFat = consumedFoods.map { it.fat }.sum()

    val backgroundColor = Color(0xFFF4F5F8)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF1E1E1E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(top = 48.dp, start = 24.dp, end = 24.dp)
    ) {
        // هدر
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "today.",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Light,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$totalCalories kcal",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                Text(
                    text = "Protein: ${"%.1f".format(totalProtein)}g",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Carbs: ${"%.1f".format(totalCarbs)}g | Fat: ${"%.1f".format(totalFat)}g",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            CustomFoodCanvasIcon()
        }

        Spacer(modifier = Modifier.height(32.dp))

        // دکمه دوربین
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToCamera() },
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFF2C2C2E),
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CameraAlt,
                        contentDescription = "Scan Food",
                        tint = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "AI Food Scanner",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = textColor
                    )
                    Text(
                        text = "Scan menus to get instant nutrition facts.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Consumed Today",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // لیست غذاها
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(consumedFoods) { food ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = food.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            )
                            Text(
                                text = "P: ${food.protein}g | C: ${food.carbs}g | F: ${food.fat}g",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Text(
                            text = "${food.calories} kcal",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(0xFF4CAF50),
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        // دکمه حذف
                        IconButton(onClick = { onDeleteFood(food) }) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "Delete",
                                tint = Color(0xFFFF5252) // رنگ قرمز ملایم
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomFoodCanvasIcon() {
    Canvas(modifier = Modifier.size(72.dp)) {
        val canvasSize = size.width
        val center = Offset(canvasSize / 2, canvasSize / 2)

        drawRoundRect(
            color = Color(0xFFD1D9E6),
            topLeft = Offset(4f, canvasSize / 2f),
            size = Size(canvasSize - 8f, canvasSize / 3f),
            cornerRadius = CornerRadius(canvasSize / 2f, canvasSize / 2f),
            style = Stroke(width = 6f)
        )

        val path = Path().apply {
            moveTo(canvasSize / 2, canvasSize / 2 + 10f)
            quadraticBezierTo(
                canvasSize / 2 + 25f, canvasSize / 4,
                canvasSize / 2, 10f
            )
            quadraticBezierTo(
                canvasSize / 2 - 25f, canvasSize / 4,
                canvasSize / 2, canvasSize / 2 + 10f
            )
        }
        drawPath(
            path = path,
            color = Color(0xFF4CAF50)
        )
    }
}