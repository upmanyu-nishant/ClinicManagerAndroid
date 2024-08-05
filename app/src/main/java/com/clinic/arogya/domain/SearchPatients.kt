package com.clinic.arogya.domain

import com.clinic.arogya.domain.model.Patient

class SearchPatients {
    fun execute(notes: List<Patient>, query: String): List<Patient> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.name.trim().lowercase().contains(query.lowercase())
        }
    }
}