package com.example.doctorplant.modules

import androidx.room.Room
import com.example.doctorplant.data.local.AppDatabase
import com.example.doctorplant.data.remote.DiagnosisApi
import com.example.doctorplant.data.repository.DiagnosisRepository
import com.example.doctorplant.ui.diagnosis.DiagnosisViewModel
import com.example.doctorplant.ui.history.HistoryViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
    single {
        Retrofit.Builder()
            .baseUrl("https://ju-am-soja-api.hf.space/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiagnosisApi::class.java)
    }
    single {
        val db = Room.databaseBuilder(androidContext(), AppDatabase::class.java, "plant_db").build()
        db.diagnosisDao()
    }
    singleOf(::DiagnosisRepository)
    viewModelOf(::DiagnosisViewModel)
    viewModelOf(::HistoryViewModel)

}