package com.meggy.doctorplant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.meggy.doctorplant.data.model.DiagnosisHistory
import com.meggy.doctorplant.utils.Converters

@Database(entities = [DiagnosisHistory::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diagnosisDao(): DiagnosisDao
}