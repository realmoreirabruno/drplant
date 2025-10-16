package com.example.doctorplant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doctorplant.data.model.DiagnosisHistory

@Database(entities = [DiagnosisHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diagnosisDao(): DiagnosisDao
}