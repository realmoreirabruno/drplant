package com.example.doctorplant.data.repository

import android.util.Log
import com.example.doctorplant.data.local.DiagnosisDao
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.data.model.PlantDisease
import com.example.doctorplant.data.remote.DiagnosisApi
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
        val multipart = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)

        try {
            val response = api.diagnosePlant(multipart)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("API_ERROR", "Code: ${response.code()} Message: ${response.errorBody()?.string()}")
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    suspend fun saveHistory(history: DiagnosisHistory) {
        dao.insert(history)
    }

    fun getAllHistory() = dao.getAll()
}
