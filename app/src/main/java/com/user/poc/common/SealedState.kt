package com.user.poc.common

import com.user.poc.model.Nationalize
import com.user.poc.model.PopulationData

sealed class PopulationState {
    object Loading : PopulationState()
    data class Success(val populationList: List<PopulationData>) : PopulationState()
    data class Error(val message: String) : PopulationState()
}

sealed class NationalityState {
    object Loading : NationalityState()
    data class Success(val result: Nationalize) : NationalityState()
    data class Error(val message: String) : NationalityState()
}