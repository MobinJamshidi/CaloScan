package com.mobinjam.caloscan.di

import androidx.room.Room
import com.mobinjam.caloscan.data.local.AppDatabase
import com.mobinjam.caloscan.data.repository.FoodRepositoryImpl
import com.mobinjam.caloscan.domain.repository.FoodRepository
import com.mobinjam.caloscan.presentation.camera.MenuScannerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ساخت دیتابیس به صورت سینگلتون
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "caloscan_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // معرفی DAO
    single { get<AppDatabase>().foodDao() }

    // معرفی ریپازیتوری به همراه پاس دادن Context
    single<FoodRepository> { FoodRepositoryImpl(get(), androidContext()) }

    // معرفی ViewModel
    viewModel { MenuScannerViewModel(get()) }
}