package com.clinic.arogya.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.clinic.arogya.data.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM PatientEntity")
    fun getAllPatient(): Flow<List<PatientEntity>>

    @Query(
        """
        SELECT * FROM PatientEntity
        WHERE id = :id
    """
    )
    suspend fun getPatientById(id: Int): PatientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patientEntity: PatientEntity)

    @Delete
    suspend fun deletePatient(patientEntity: PatientEntity)

    @Update
    suspend fun updatePatient(patientEntity: PatientEntity)
}