package com.mobinjam.caloscan.di

import androidx.room.Room
import com.mobinjam.caloscan.data.local.AppDatabase
import com.mobinjam.caloscan.data.repository.FoodRepositoryImpl
import com.mobinjam.caloscan.domain.repository.FoodRepository
import com.mobinjam.caloscan.presentation.camera.MenuScannerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "caloscan_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().foodDao }

    single<FoodRepository> { FoodRepositoryImpl(get()) }

    viewModel { MenuScannerViewModel(get()) }
}