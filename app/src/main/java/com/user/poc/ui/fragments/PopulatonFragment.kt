package com.user.poc.ui.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.user.poc.common.LoadingIndicator
import com.user.poc.common.PopulationState
import com.user.poc.model.PopulationItem
import com.user.poc.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopulationFragment(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.populationUiState

    LaunchedEffect(Unit) {
        viewModel.loadPopulation()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Population Data") },
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
                is PopulationState.Loading -> {
                    LoadingIndicator()
                }

                is PopulationState.Error -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(uiState.message)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadPopulation() }) {
                            Text("Retry")
                        }
                    }
                }

                is PopulationState.Success -> {
                    LazyColumn {
                        items(uiState.populationList) { item ->
                            PopulationItem(item = item)
                        }
                    }
                }
            }
        }
    }
}