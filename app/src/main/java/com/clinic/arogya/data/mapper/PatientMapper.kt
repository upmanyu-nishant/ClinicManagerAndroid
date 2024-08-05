package com.clinic.arogya.data.mapper

import com.clinic.arogya.data.local.entity.PatientEntity
import com.clinic.arogya.domain.model.Patient

fun PatientEntity.asExternalModel(): Patient = Patient(
    id,
    name,
    sex,
     phoneNo,
     age,
    address,
    symptoms,
    diagnosis,
    treatment,
    medicine
)

fun Patient.toEntity(): PatientEntity = PatientEntity(
    id, name,
    sex,
    phoneNo,
    age,
    address,
    symptoms,
    diagnosis,
    treatment,
    medicine
)