package com.example.mathsapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(
        BottomNavigationRoute.Home,
        BottomNavigationRoute.History,
    )
    BottomAppBar(
        modifier = Modifier.fillMaxWidth().heightIn(max = 50.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: "home"
        val selectedItem = remember { mutableStateOf(currentRoute) }
        val navbarItemColors = NavigationBarItemDefaults.colors(
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primary,
        )
        screens.forEach { screen ->
            NavigationBarItem(
                selected = selectedItem.value == screen.path,
                onClick = {
                    selectedItem.value = screen.path
                    navController.navigate(screen.path)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title
                    )
                },
                modifier = Modifier.padding(10.dp),
                colors = navbarItemColors
            )
        }
    }
}
