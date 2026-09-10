package com.mobinjam.caloscan

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CaloScanApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // راه‌اندازی Koin به محض اجرای اپلیکیشن
        startKoin {
            androidContext(this@CaloScanApp)
            // modules(appModule) // این خط رو فعلاً کامنت کردیم تا بعداً ماژول‌ها رو بسازیم
        }
    }
}