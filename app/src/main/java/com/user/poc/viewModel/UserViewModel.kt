package com.user.poc.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.poc.model.User
import com.user.poc.network.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var userList by mutableStateOf<List<User>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun loadUsers() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                userList = repository.fetchUsers()
            } catch (e: Exception) {
                errorMessage = "Failed to load users"
            }
            isLoading = false
        }
    }

    fun addUser(name: String, email: String) {
        viewModelScope.launch {
            try {
                val newUser = User(name = name, email = email)
                repository.createUser(newUser)
                loadUsers()
            } catch (e: Exception) {
                errorMessage = "Failed to add user"
            }
        }
    }

    fun deleteUser(id: String) {
        viewModelScope.launch {
            try {
                repository.deleteUser(id)
                loadUsers()
            } catch (e: Exception) {
                errorMessage = "Failed to delete user"
            }
        }
    }
}