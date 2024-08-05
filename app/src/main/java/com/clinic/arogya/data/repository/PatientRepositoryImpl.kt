package com.clinic.arogya.data.repository

import com.clinic.arogya.data.local.dao.PatientDao
import com.clinic.arogya.data.mapper.asExternalModel
import com.clinic.arogya.data.mapper.toEntity
import com.clinic.arogya.domain.model.Patient
import com.clinic.arogya.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PatientRepositoryImpl(
    private val dao: PatientDao
) : PatientRepository {

    override fun getAllPatient(): Flow<List<Patient>> {
        return dao.getAllPatient()
            .map { patient ->
                patient.map {
                    it.asExternalModel()
                }
            }
    }

    override suspend fun getPatientById(id: Int): Patient? {
        return dao.getPatientById(id)?.asExternalModel()
    }

    override suspend fun insertPatient(patient: Patient) {
        dao.insertPatient(patient.toEntity())
    }

    override suspend fun deletePatient(patient: Patient) {
        dao.deletePatient(patient.toEntity())
    }

    override suspend fun updatePatient(patient: Patient) {
        dao.updatePatient(patient.toEntity())
    }
}