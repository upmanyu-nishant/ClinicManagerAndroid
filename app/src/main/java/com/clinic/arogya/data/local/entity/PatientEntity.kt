package com.clinic.arogya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PatientEntity(
    @PrimaryKey
    val id: Int?,
    val name: String,
    val age: String,
    val sex: String,
    val phoneNo: String,
    val address: String,
    val symptoms: String,
    val diagnosis: String,
    val treatment: String,
    val medicine: String,
    val date: String

)
