package com.user.poc.ui.fragments

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.user.poc.viewModel.UserViewModel

@Composable
fun UserFragment(viewModel: UserViewModel = hiltViewModel()) {
    val users = viewModel.userList
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New User", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Button(onClick = {
            if (name.isNotBlank() && email.isNotBlank()) {
                viewModel.addUser(name, email)
                name = ""
                email = ""
            } else {
                Toast.makeText(context, "Enter both name and email", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Add User")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(users) { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Name: ${user.name}")
                            Text(text = "Email: ${user.email}")
                        }
                        IconButton(onClick = {
                            user.id?.let { viewModel.deleteUser(it) }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete User")
                        }
                    }
                    Divider()
                }
            }
        }
    }
}