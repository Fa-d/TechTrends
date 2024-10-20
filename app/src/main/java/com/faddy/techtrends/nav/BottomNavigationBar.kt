package com.faddy.techtrends.nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(
        BottomNavItem.NEWSFEED, BottomNavItem.FAV, BottomNavItem.SAVED, BottomNavItem.PROFILE
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { item ->
            NavigationBarItem(selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) })
        }
    }
}