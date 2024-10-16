package com.faddy.techtrends.nav

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faddy.techtrends.core.MainViewModel
import com.faddy.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import com.faddy.techtrends.nav.NavScreens.NEWS_DETAILS_SCREEN
import com.faddy.techtrends.nav.NavScreens.PROFILE_SCREEN
import com.faddy.techtrends.nav.NavScreens.SPLASH_SCREEN
import com.faddy.techtrends.nav.NavScreens.TOPIC_SELECT_SCREEN
import com.faddy.techtrends.nav.NavScreens.WELCOME_SCREEN
import com.faddy.techtrends.ui.screens.SplashScreen
import com.faddy.techtrends.ui.screens.WelcomeScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun RMNavGraph(
    navController: NavHostController,
    startDestination: String = SPLASH_SCREEN,
    scope: CoroutineScope = rememberCoroutineScope(),
    navActions: RMNavActions = remember(navController) { RMNavActions(navController) }
) {

    val mainViewModel: MainViewModel = hiltViewModel()
    val context = LocalContext.current


    NavHost(navController = navController, startDestination = startDestination) {
        composable(SPLASH_SCREEN) {
            SplashScreen()
            LaunchedEffect(key1 = "") {
                Handler(Looper.getMainLooper()).postDelayed({
                    navActions.navigateToWelcomeScreen()
                }, 500L)
            }
        }
        composable(WELCOME_SCREEN) {
            WelcomeScreen()
        }
        composable(TOPIC_SELECT_SCREEN) {}
        composable(NEWSFEED_SCREEN) {

        }
        composable(NEWS_DETAILS_SCREEN) {

        }
        composable(PROFILE_SCREEN) {

        }
    }
}