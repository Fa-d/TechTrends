package dev.experimental.techtrends.nav

import dev.experimental.techtrends.R
import dev.experimental.techtrends.nav.NavScreens.FAV_SCREEN
import dev.experimental.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import dev.experimental.techtrends.nav.NavScreens.PROFILE_SCREEN
import dev.experimental.techtrends.nav.NavScreens.SAVED_SCREEN

sealed class BottomNavItem(val route: String, val iconResId: Int, val label: String) {
    data object FAV : BottomNavItem(FAV_SCREEN, R.drawable.star, "Favorite")
    data object NEWSFEED : BottomNavItem(NEWSFEED_SCREEN, R.drawable.home_icon, "Home")

    data object SAVED : BottomNavItem(SAVED_SCREEN, R.drawable.menu, "Read Later")
    data object PROFILE :
        BottomNavItem(PROFILE_SCREEN, R.drawable.baseline_account_circle_24, "PROFILE")

}
