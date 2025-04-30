package com.user.poc.network

import com.user.poc.di.NationalizeApi
import com.user.poc.di.PopulationApi
import com.user.poc.model.Nationalize
import com.user.poc.model.PopulationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    @PopulationApi private val apiService: ApiService,
    @NationalizeApi private val nationalizeService: NationalizeService
) {
    suspend fun fetchPopulation(): List<PopulationData> {
        return apiService.getPopulation().data
    }
    suspend fun fetchNationality(name:String): Nationalize {
        return nationalizeService.getNationality(name)
    }
}