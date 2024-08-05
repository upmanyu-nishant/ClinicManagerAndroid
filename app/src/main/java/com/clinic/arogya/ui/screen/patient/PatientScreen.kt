@file:OptIn(ExperimentalMaterial3Api::class)

package com.clinic.arogya.ui.screen.patient

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PatientScreen(
    state: PatientState,
    onEvent: (PatientEvent) -> Unit
) {
    val isFormValid by rememberUpdatedState(
        state.name.isNotBlank() &&
                isValidAge(state.age) &&
                state.sex.isNotBlank() &&
                isValidPhoneNo(state.phoneNo) &&
                state.address.isNotBlank()
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Patient Details", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(PatientEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(PatientEvent.DeletePatient)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Personal Information",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Name
            item {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(PatientEvent.NameChange(it))
                    },
                    label = { Text("Name") },
                    isError = state.name.isBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                if (state.name.isBlank()) {
                    Text("Name is required", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
            }

            // Age
            item {
                OutlinedTextField(
                    value = state.age,
                    onValueChange = {
                        onEvent(PatientEvent.AgeChange(it))
                    },
                    label = { Text("Age") },
                    isError = !isValidAge(state.age),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                if (!isValidAge(state.age)) {
                    Text("Age must be a valid number", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
            }

            // Sex
            item {
                OutlinedTextField(
                    value = state.sex,
                    onValueChange = {
                        onEvent(PatientEvent.SexChange(it))
                    },
                    label = { Text("Sex") },
                    isError = state.sex.isBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                if (state.sex.isBlank()) {
                    Text("Sex is required", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
            }

            // Phone Number
            item {
                OutlinedTextField(
                    value = state.phoneNo,
                    onValueChange = {
                        onEvent(PatientEvent.PhoneNoChange(it))
                    },
                    label = { Text("Phone Number") },
                    isError = !isValidPhoneNo(state.phoneNo),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                if (!isValidPhoneNo(state.phoneNo)) {
                    Text("Phone Number must contain only digits", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }

            }

            // Address
            item {
                OutlinedTextField(
                    value = state.address,
                    onValueChange = {
                        onEvent(PatientEvent.AddressChange(it))
                    },
                    label = { Text("Address") },
                    isError = state.address.isBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )
                if (state.address.isBlank()) {
                    Text("Address is required", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
            }

            item {
                Text(
                    text = "Medical Information",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Symptoms
            item {
                OutlinedTextField(
                    value = state.symptoms,
                    onValueChange = {
                        onEvent(PatientEvent.SymptomsChange(it))
                    },
                    label = { Text("Symptoms") },
                    modifier = Modifier.fillMaxWidth(),

                    )
            }

            // Diagnosis
            item {
                OutlinedTextField(
                    value = state.diagnosis,
                    onValueChange = {
                        onEvent(PatientEvent.DiagnosisChange(it))
                    },
                    label = { Text("Diagnosis") },
                    modifier = Modifier.fillMaxWidth(),

                    )
            }

            // Treatment
            item {
                OutlinedTextField(
                    value = state.treatment,
                    onValueChange = {
                        onEvent(PatientEvent.TreatmentChange(it))
                    },
                    label = { Text("Treatment") },
                    modifier = Modifier.fillMaxWidth(),

                    )
            }

            // Medicine
            item {
                OutlinedTextField(
                    value = state.medicine,
                    onValueChange = {
                        onEvent(PatientEvent.MedicineChange(it))
                    },
                    label = { Text("Medicine") },
                    modifier = Modifier.fillMaxWidth(),

                    )
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onEvent(PatientEvent.Save)
                        },
                        enabled = isFormValid, // Enable only if the form is valid
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(top = 16.dp)
                    ) {
                        Text(text = "Save", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

fun isValidAge(age: String): Boolean {
    return age.toIntOrNull() != null && age.toInt() > 0
}

fun isValidPhoneNo(phoneNo: String): Boolean {
    return phoneNo.all { it.isDigit() } && phoneNo.length >= 10 // Example: Phone number should be at least 10 digits
}
