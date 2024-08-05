package com.clinic.arogya.domain.repository

import com.clinic.arogya.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository {

    fun getAllPatient(): Flow<List<Patient>>

    suspend fun getPatientById(id: Int): Patient?

    suspend fun insertPatient(patient: Patient)

    suspend fun deletePatient(patient: Patient)

    suspend fun updatePatient(patient: Patient)
}