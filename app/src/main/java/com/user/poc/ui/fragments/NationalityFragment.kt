package com.user.poc.ui.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.user.poc.common.LoadingIndicator
import com.user.poc.common.NationalityState
import com.user.poc.utils.format
import com.user.poc.viewModel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NationalityFragment(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.nationalityUiState
    val name = "nathaniel"

    LaunchedEffect(Unit) {
        viewModel.loadNationality(name)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nationality Estimator") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when (uiState) {
                is NationalityState.Loading -> {
                    LoadingIndicator()
                }

                is NationalityState.Error -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(uiState.message)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadNationality(name) }) {
                            Text("Retry")
                        }
                    }
                }

                is NationalityState.Success -> {
                    val result = uiState.result

                    Column {
                        Text("Name: ${result.name}", style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(16.dp))

                        result.country.take(5).forEach { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("Country: ${item.country_id}", style = MaterialTheme.typography.titleMedium)
                                    LinearProgressIndicator(
                                        progress = item.probability.toFloat(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(8.dp),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Probability: ${(item.probability * 100).format(2)}%",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}