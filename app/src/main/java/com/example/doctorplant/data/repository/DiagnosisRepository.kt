package com.example.doctorplant.data.repository

import com.example.doctorplant.data.local.DiagnosisDao
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.data.model.PlantDisease
import com.example.doctorplant.data.remote.DiagnosisApi
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DiagnosisRepository(
    private val api: DiagnosisApi,
    private val dao: DiagnosisDao
) {
    suspend fun diagnosePlant(imageFile: File): PlantDisease? {
        val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val multipart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        val response = api.diagnosePlant(multipart)
        return if (response.isSuccessful) response.body() else null
    }

    fun getHistory(): Flow<List<DiagnosisHistory>> = dao.getAll()

    suspend fun saveDiagnosis(history: DiagnosisHistory) = dao.insert(history)
}
