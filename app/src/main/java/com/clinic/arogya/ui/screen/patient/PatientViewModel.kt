package com.clinic.arogya.ui.screen.patient

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clinic.arogya.domain.model.Patient
import com.clinic.arogya.domain.repository.PatientRepository
import com.clinic.arogya.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(
    private val repository: PatientRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(PatientState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let {
            val id = it.toInt()
            viewModelScope.launch {
                repository.getPatientById(id)?.let { patient ->
                    _state.update { screenState ->
                        screenState.copy(
                            id = patient.id,
                            name = patient.name,
                            age = patient.age,
                            sex = patient.sex,
                            phoneNo = patient.phoneNo,
                            address = patient.address,
                            symptoms = patient.symptoms,
                            diagnosis = patient.diagnosis,
                            treatment = patient.treatment,
                            medicine = patient.medicine,
                            date = patient.date
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: PatientEvent) {
        when (event) {
            is PatientEvent.NameChange -> {
                _state.update {
                    it.copy(
                        name = event.value
                    )
                }
            }

            is PatientEvent.AgeChange -> {
                _state.update {
                    it.copy(
                        age = event.value
                    )
                }
            }

            is PatientEvent.SexChange -> {
                _state.update {
                    it.copy(
                        sex = event.value
                    )
                }
            }

            is PatientEvent.PhoneNoChange -> {
                _state.update {
                    it.copy(
                        phoneNo = event.value
                    )
                }
            }

            is PatientEvent.AddressChange -> {
                _state.update {
                    it.copy(
                        address = event.value
                    )
                }
            }

            is PatientEvent.SymptomsChange -> {
                _state.update {
                    it.copy(
                        symptoms = event.value
                    )
                }
            }

            is PatientEvent.DiagnosisChange -> {
                _state.update {
                    it.copy(
                        diagnosis = event.value
                    )
                }
            }

            is PatientEvent.TreatmentChange -> {
                _state.update {
                    it.copy(
                        treatment = event.value
                    )
                }
            }

            is PatientEvent.MedicineChange -> {
                _state.update {
                    it.copy(
                        medicine = event.value
                    )
                }
            }
            is PatientEvent.DateChange -> {
                _state.update {
                    it.copy(
                        date = event.value
                    )
                }
            }





            PatientEvent.NavigateBack -> sendEvent(UiEvent.NavigateBack)
            PatientEvent.Save -> {
                viewModelScope.launch {
                    val state = state.value
                    val patient = Patient(
                        id = state.id,
                        name = state.name,
                        age = state.age,
                        sex = state.sex,
                        phoneNo = state.phoneNo,
                        address = state.address,
                        symptoms = state.symptoms,
                        diagnosis = state.diagnosis,
                        treatment = state.treatment,
                        medicine = state.medicine,
                        date = state.date,


                        )
                    if (state.id == null) {
                        repository.insertPatient(patient)
                    } else {
                        repository.updatePatient(patient)
                    }
                    sendEvent(UiEvent.NavigateBack)
                }
            }

            PatientEvent.DeletePatient -> {
                viewModelScope.launch {
                    val state = state.value
                    val patient = Patient(
                        id = state.id,
                        name = state.name,
                        age = state.age,
                        sex = state.sex,
                        phoneNo = state.phoneNo,
                        address = state.address,
                        symptoms = state.symptoms,
                        diagnosis = state.diagnosis,
                        treatment = state.treatment,
                        medicine = state.medicine,
                        date = state.date
                        )
                    repository.deletePatient(patient)
                }
                sendEvent(UiEvent.NavigateBack)
            }
        }
    }
}