package com.example.doctorplant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.utils.Converters

@Database(entities = [DiagnosisHistory::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diagnosisDao(): DiagnosisDao
}