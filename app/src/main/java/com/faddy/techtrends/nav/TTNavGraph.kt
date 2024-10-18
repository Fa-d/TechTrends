package com.faddy.techtrends.nav

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faddy.techtrends.nav.NavScreens.FAV_SCREEN
import com.faddy.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import com.faddy.techtrends.nav.NavScreens.NEWS_DETAILS_SCREEN
import com.faddy.techtrends.nav.NavScreens.PROFILE_SCREEN
import com.faddy.techtrends.nav.NavScreens.SAVED_SCREEN
import com.faddy.techtrends.nav.NavScreens.SPLASH_SCREEN
import com.faddy.techtrends.nav.NavScreens.TOPIC_SELECT_SCREEN
import com.faddy.techtrends.nav.NavScreens.WELCOME_SCREEN
import com.faddy.techtrends.ui.screens.FavScreen
import com.faddy.techtrends.ui.screens.NewsFeedScreen
import com.faddy.techtrends.ui.screens.ProfileScreen
import com.faddy.techtrends.ui.screens.SavedScreen
import com.faddy.techtrends.ui.screens.SplashScreen
import com.faddy.techtrends.ui.screens.TopicSelectScreen
import com.faddy.techtrends.ui.screens.WelcomeScreen
import com.faddy.techtrends.utils.LocalNavController


@Composable
fun RMNavGraph(
    navController: NavHostController, startDestination: String = NEWSFEED_SCREEN
) {
    val currentNav = LocalNavController.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(SPLASH_SCREEN) {
            SplashScreen()
            LaunchedEffect(key1 = "") {
                Handler(Looper.getMainLooper()).postDelayed({
                    currentNav.navigate(WELCOME_SCREEN) { launchSingleTop = true }
                }, 500L)
            }
        }
        composable(WELCOME_SCREEN) {
            WelcomeScreen()
        }
        composable(TOPIC_SELECT_SCREEN) {
            TopicSelectScreen()
        }
        composable(NEWSFEED_SCREEN) {
            NewsFeedScreen()
        }
        composable(NEWS_DETAILS_SCREEN) {

        }
        composable(PROFILE_SCREEN) {
            ProfileScreen()
        }
        composable(FAV_SCREEN) {
            FavScreen()
        }
        composable(SAVED_SCREEN) {
            SavedScreen()
        }
    }
}