package com.example.doctorplant.data.remote

import com.example.doctorplant.data.model.PlantDisease
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

fun interface DiagnosisApi {
    @Multipart
    @Headers("Authorization: Bearer minha-api-soja-api-secrettoken-951753")
    @POST("classify/")
    suspend fun diagnosePlant(
        @Part image: MultipartBody.Part
    ): Response<PlantDisease>
}