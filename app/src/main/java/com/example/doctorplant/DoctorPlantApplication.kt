package com.example.doctorplant

import android.app.Application
import com.example.doctorplant.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorPlantApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DoctorPlantApplication)
            modules(appModule)
        }
    }
}