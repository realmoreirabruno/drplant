package com.example.doctorplant.data.remote

import com.example.doctorplant.data.model.PlantDisease
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DiagnosisApi {
    @Multipart
    @POST("predict")
    suspend fun diagnosePlant(
        @Part image: MultipartBody.Part
    ): Response<PlantDisease>
}