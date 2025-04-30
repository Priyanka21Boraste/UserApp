package com.user.poc.network

import com.user.poc.model.Nationalize
import com.user.poc.model.Population
import com.user.poc.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/data")
    suspend fun getPopulation(
        @Query("drilldowns") drilldowns: String = "Nation",
        @Query("measures") measures: String = "Population"
    ): Population
}
interface NationalizeService{
    @GET(".")
    suspend fun getNationality(
        @Query("name") name: String): Nationalize
}

interface UserService{
    @GET("users")
    suspend fun getUsers():List<User>

    @POST("users")
    suspend fun addUser(@Body user: User):User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id:String): Response<Unit>
}