package com.user.poc.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.poc.common.NationalityState
import com.user.poc.common.PopulationState
import com.user.poc.network.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    var populationUiState by mutableStateOf<PopulationState>(PopulationState.Loading)
        private set

    var nationalityUiState by mutableStateOf<NationalityState>(NationalityState.Loading)
        private set

    fun loadPopulation() {
        viewModelScope.launch {
            populationUiState = PopulationState.Loading
            try {
                val data = repository.fetchPopulation()
                populationUiState = PopulationState.Success(data)
            } catch (e: Exception) {
                populationUiState = PopulationState.Error("Failed to load population data.")
            }
        }
    }

    fun loadNationality(name: String) {
        viewModelScope.launch {
            nationalityUiState = NationalityState.Loading
            try {
                val result = repository.fetchNationality(name)
                nationalityUiState = NationalityState.Success(result)
            } catch (e: Exception) {
                nationalityUiState = NationalityState.Error("Failed to load nationality.")
            }
        }
    }
}