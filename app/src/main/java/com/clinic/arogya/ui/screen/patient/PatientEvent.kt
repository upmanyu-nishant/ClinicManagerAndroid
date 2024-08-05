package com.clinic.arogya.ui.screen.patient

sealed interface PatientEvent {
    data class NameChange(val value: String): PatientEvent
    data class AgeChange(val value: String): PatientEvent
    data class SexChange(val value: String): PatientEvent
    data class PhoneNoChange(val value: String): PatientEvent
    data class AddressChange(val value: String): PatientEvent
    data class SymptomsChange(val value: String): PatientEvent
    data class DiagnosisChange(val value: String): PatientEvent
    data class TreatmentChange(val value: String): PatientEvent
    data class MedicineChange(val value: String): PatientEvent
    object Save : PatientEvent
    object NavigateBack : PatientEvent
    object DeletePatient : PatientEvent
}