package com.user.poc.di

import com.user.poc.network.ApiService
import com.user.poc.network.NationalizeService
import com.user.poc.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @PopulationApi
    @Provides
    fun providePopulationRetrofit(): Retrofit =
        createRetrofit("https://datausa.io/")

    @NationalizeApi
    @Provides
    fun provideNationalizeRetrofit(): Retrofit =
        createRetrofit("https://api.nationalize.io/")


    //created list,add,delete api using mockAPI
    @UserApi
    @Provides
    fun provideUserRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://6811f5233ac96f7119a646f0.mockapi.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @PopulationApi
    @Provides
    fun providePopulationApi(@PopulationApi retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @NationalizeApi
    @Provides
    fun provideNationalizeApi(@NationalizeApi retrofit: Retrofit): NationalizeService =
        retrofit.create(NationalizeService::class.java)

    @UserApi
    @Provides
    fun provideUserApi(@UserApi retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

}

