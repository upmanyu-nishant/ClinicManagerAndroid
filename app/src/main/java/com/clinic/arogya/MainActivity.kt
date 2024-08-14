package com.clinic.arogya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clinic.arogya.ui.screen.patient.PatientScreen
import com.clinic.arogya.ui.screen.patient.PatientViewModel
import com.clinic.arogya.ui.screen.patient_list.PatientListScreen
import com.clinic.arogya.ui.screen.patient_list.PatientListViewModel
import com.clinic.arogya.ui.theme.ClinicAppCourseTheme
import com.clinic.arogya.ui.util.Route
import com.clinic.arogya.ui.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClinicAppCourseTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.patientList
                ) {
                    composable(route = Route.patientList) {
                        val viewModel = hiltViewModel<PatientListViewModel>()

                        PatientListScreen(
                            viewModel = viewModel,
                            onPatientClick = { patient ->
                                navController.navigate(
                                    Route.patient.replace(
                                        "{id}",
                                        patient.id.toString()
                                    )
                                )
                            },
                            onAddPatientClick = {
                                navController.navigate(Route.patient)
                            }
                        )
                    }

                    composable(route = Route.patient) {
                        val viewModel = hiltViewModel<PatientViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect { event ->
                                when (event) {
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }
                                    // Handle other events if needed
                                    else -> Unit
                                }
                            }
                        }

                        PatientScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}
