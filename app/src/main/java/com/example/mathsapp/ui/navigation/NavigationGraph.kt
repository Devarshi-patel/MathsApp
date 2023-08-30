package com.example.mathsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mathsapp.ui.screens.HistoryScreen
import com.example.mathsapp.ui.screens.MainScreen
import com.example.mathsapp.viewmodel.MainViewModel

@Composable
fun NavigationGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = BottomNavigationRoute.Home.path) {
        composable(route = BottomNavigationRoute.Home.path) {
            MainScreen(mainViewModel)
        }
        composable(route = BottomNavigationRoute.History.path) {
            HistoryScreen(mainViewModel.results.value)
        }
    }
}
