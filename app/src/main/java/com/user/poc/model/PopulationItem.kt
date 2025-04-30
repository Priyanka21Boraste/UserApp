package com.user.poc.model

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

@Composable
fun PopulationItem(item: PopulationData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
           // Text("Nation: ${item.nation}", style = MaterialTheme.typography.titleMedium)
            Text("Year: ${item.Year}", style = MaterialTheme.typography.bodyMedium)
            Text("Population: ${item.Population}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
