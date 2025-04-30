package com.user.poc.model

data class Nationalize(
    val name: String,
    val country: List<CountryProbability>
)

data class CountryProbability(
    val country_id: String,
    val probability: Double
)
