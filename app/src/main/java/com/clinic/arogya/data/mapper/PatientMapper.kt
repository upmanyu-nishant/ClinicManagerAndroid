package com.clinic.arogya.data.mapper

import com.clinic.arogya.data.local.entity.PatientEntity
import com.clinic.arogya.domain.model.Patient

fun PatientEntity.asExternalModel(): Patient = Patient(
    id,
    name,
    age,
    sex,
    phoneNo,
    address,
    symptoms,
    diagnosis,
    treatment,
    medicine,
    date
)

fun Patient.toEntity(): PatientEntity = PatientEntity(
    id,
    name,
    age,
    sex,
    phoneNo,
    address,
    symptoms,
    diagnosis,
    treatment,
    medicine,
    date
)