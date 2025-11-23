package com.example.doctorplant.modules

import com.example.doctorplant.data.remote.DiagnosisApi
import com.example.doctorplant.data.repository.DiagnosisRepository
import com.example.doctorplant.ui.diagnosis.DiagnosisViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS) // Tempo para estabelecer conexão
        .readTimeout(120, TimeUnit.SECONDS)   // Tempo esperando a resposta (CRÍTICO para IA)
        .writeTimeout(60, TimeUnit.SECONDS)   // Tempo para enviar a imagem
        .build()
    single {
        Retrofit.Builder()
            .baseUrl("https://ju-am-soja-api.hf.space/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiagnosisApi::class.java)
    }
    viewModelOf(::DiagnosisViewModel)
    singleOf(::DiagnosisRepository)
}