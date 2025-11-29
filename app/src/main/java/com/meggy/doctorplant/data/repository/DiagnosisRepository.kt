package com.meggy.doctorplant.data.repository

import android.util.Log
import com.meggy.doctorplant.data.local.DiagnosisDao
import com.meggy.doctorplant.data.model.DiagnosisHistory
import com.meggy.doctorplant.data.model.PlantDisease
import com.meggy.doctorplant.data.remote.DiagnosisApi
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

    fun getAllHistory() = dao.getAll()

    suspend fun saveHistory(history: DiagnosisHistory) {
        dao.insert(history)
    }

    suspend fun deleteHistoryItems(items: List<DiagnosisHistory>) {
        dao.deleteList(items)
    }
}
