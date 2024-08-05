package com.clinic.arogya.domain.model

data class Patient(
    val id: Int? = null,
    val name: String = "",
    val age: String = "",
    val sex: String= "",
    val phoneNo: String="",
    val address: String="",
    val symptoms: String="",
    val diagnosis: String="",
    val treatment: String="",
    val medicine: String="",

)


