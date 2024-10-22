package com.faddy.techtrends.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.faddy.techtrends.nav.NavScreens.FAV_SCREEN
import com.faddy.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import com.faddy.techtrends.nav.NavScreens.PROFILE_SCREEN
import com.faddy.techtrends.nav.NavScreens.SAVED_SCREEN

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object NEWSFEED : BottomNavItem(NEWSFEED_SCREEN, Icons.Outlined.Home, "Home")
    data object FAV : BottomNavItem(FAV_SCREEN, Icons.Outlined.Star, "Favorite")
    data object SAVED : BottomNavItem(SAVED_SCREEN, Icons.Default.List, "SAVED")
    data object PROFILE : BottomNavItem(PROFILE_SCREEN, Icons.Outlined.Person, "PROFILE")
}
