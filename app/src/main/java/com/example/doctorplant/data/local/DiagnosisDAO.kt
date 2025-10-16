package com.example.doctorplant.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.doctorplant.data.model.DiagnosisHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface DiagnosisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diagnosis: DiagnosisHistory)

    @Query("SELECT * FROM diagnosis_history ORDER BY date DESC")
    fun getAll(): Flow<List<DiagnosisHistory>>

    @Delete
    suspend fun delete(diagnosis: DiagnosisHistory)
}