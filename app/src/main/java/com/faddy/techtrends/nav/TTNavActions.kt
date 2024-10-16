package com.faddy.techtrends.nav

import androidx.navigation.NavHostController
import com.faddy.techtrends.nav.NavScreens.SPLASH_SCREEN
import com.faddy.techtrends.nav.NavScreens.TOPIC_SELECT_SCREEN
import com.faddy.techtrends.nav.NavScreens.WELCOME_SCREEN


class RMNavActions(private val navController: NavHostController) {
    fun navigateToSplashScreen() {
        navController.navigate(SPLASH_SCREEN) { launchSingleTop = true }
    }

    fun navigateToWelcomeScreen() {
        navController.navigate(WELCOME_SCREEN) { launchSingleTop = true }
    }

    fun navigateToTopicSelectScreen() {
        navController.navigate(TOPIC_SELECT_SCREEN) { launchSingleTop = true }
    }


}