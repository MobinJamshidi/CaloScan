package com.mobinjam.caloscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mobinjam.caloscan.presentation.camera.MenuScannerScreen
import com.mobinjam.caloscan.ui.theme.CaloScanTheme // ممکنه اسم پکیج تم شما کمی متفاوت باشه

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloScanTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MenuScannerScreen()
                }
            }
        }
    }
}