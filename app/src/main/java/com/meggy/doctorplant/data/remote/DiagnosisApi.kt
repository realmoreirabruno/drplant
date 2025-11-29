package com.meggy.doctorplant.data.remote

import com.meggy.doctorplant.data.model.PlantDisease
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

fun interface DiagnosisApi {
    @Multipart
    @POST("classify/")
    suspend fun diagnosePlant(
        @Part image: MultipartBody.Part
    ): Response<PlantDisease>
}