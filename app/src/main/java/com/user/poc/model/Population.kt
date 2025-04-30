package com.user.poc.model

data class Population(
    val data: List<PopulationData>
)

data class PopulationData(
    val Year: String,
    val Population:String
)
