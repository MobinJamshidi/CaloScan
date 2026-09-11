package com.mobinjam.caloscan

import android.app.Application
import com.mobinjam.caloscan.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CaloScanApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CaloScanApp)
            modules(appModule)
        }
    }
}