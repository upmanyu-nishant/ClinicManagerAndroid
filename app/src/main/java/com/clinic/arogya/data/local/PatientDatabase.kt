package com.clinic.arogya.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clinic.arogya.data.local.dao.PatientDao
import com.clinic.arogya.data.local.entity.PatientEntity

@Database(
    version = 1,
    entities = [PatientEntity::class]
)
abstract class PatientDatabase: RoomDatabase() {

    abstract val dao: PatientDao

    companion object {
        const val name = "patient_db"
    }
}