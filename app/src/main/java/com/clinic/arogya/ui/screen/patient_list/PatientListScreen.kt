@file:OptIn(ExperimentalMaterial3Api::class)

package com.clinic.arogya.ui.screen.patient_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clinic.arogya.R
import com.clinic.arogya.domain.model.Patient
import com.clinic.arogya.ui.screen.patient_list.components.PatientCard

@Composable
fun PatientListScreen(
    viewModel: PatientListViewModel,
    onPatientClick: (Patient) -> Unit,
    onAddPatientClick: () -> Unit
) {
    val patientList by viewModel.patientList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredPatientList = patientList.filter { patient ->
        patient.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(vertical = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Hospital Logo",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = "PatientKeeper",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 30.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.5.sp
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.height(80.dp),
                scrollBehavior = null
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPatientClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add patient"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query -> searchQuery = query },
                label = { Text("Search by Name") },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredPatientList) { patient ->
                    PatientCard(patient, onPatientClick)
                }
            }
        }
    }
}
