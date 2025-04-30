package com.user.poc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.user.poc.ui.fragments.NationalityFragment
import com.user.poc.ui.fragments.PopulationFragment
import com.user.poc.ui.fragments.UserFragment

@Composable
    fun PopulationApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "page1") {
            composable("page1") { PopulationFragment(navController) }
            composable("page2") { NationalityFragment(navController) }
            composable("page3") { UserFragment(navController) }
 }
    }