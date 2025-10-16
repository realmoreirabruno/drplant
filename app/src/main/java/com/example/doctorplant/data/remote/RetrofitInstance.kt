package com.example.doctorplant.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: DiagnosisApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://seu-servidor.com/") //TODO: Mudar depois
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiagnosisApi::class.java)
    }
}
