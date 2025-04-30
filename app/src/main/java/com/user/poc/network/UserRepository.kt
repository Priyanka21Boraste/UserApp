package com.user.poc.network

import com.user.poc.di.UserApi
import com.user.poc.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    @UserApi private val userService: UserService
) {
    suspend fun fetchUsers() = userService.getUsers()
    suspend fun createUser(user: User) = userService.addUser(user)
    suspend fun deleteUser(id: String) = userService.deleteUser(id)
}
