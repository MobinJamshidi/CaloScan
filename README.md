# 🍏 AI Food Scanner (CaloScan)

An AI-powered Android application that scans restaurant and cafe menus in real-time, instantly extracting food names and displaying their nutritional facts (Calories, Protein, Carbs, and Fat). Built with a focus on modern Android development standards and a minimalist UI.

## ✨ Features
- **Real-Time Menu Scanning:** Uses CameraX and Google ML Kit for on-device text recognition.
- **Smart Data Matching:** Instantly matches scanned text with a local database of 250+ food items.
- **Daily Dashboard:** Tracks your daily consumed macros and calories with an auto-reset logic at midnight.
- **Offline First:** Fully functional offline using a pre-populated Room database via JSON assets.
- **Modern UI:** Built entirely with Jetpack Compose featuring custom Canvas elements and smooth animations.

## 🛠 Tech Stack
- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose
- **Architecture:** Clean Architecture + MVVM
- **Dependency Injection:** Koin
- **Local Database:** Room
- **Asynchronous Programming:** Coroutines & StateFlow
- **Machine Learning:** Google ML Kit (Text Recognition)
- **Camera:** CameraX

## 🚀 How to Run
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run on a physical device (Camera required).
