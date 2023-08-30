package com.example.mathsapp.ui.navigation

import com.example.mathsapp.R

sealed class BottomNavigationRoute(val title: String, var icon: Int, var path: String) {
    object Home: BottomNavigationRoute("Home", R.drawable.home_tab_icon, "home")
    object History: BottomNavigationRoute("History", R.drawable.history_tab_icon, "history")
}
