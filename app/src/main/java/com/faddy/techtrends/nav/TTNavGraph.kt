package com.faddy.techtrends.nav

import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faddy.techtrends.datastore.TtPref
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
    navController: NavHostController, startDestination: String = SPLASH_SCREEN
) {
    val currentNav = LocalNavController.current
    var ttPref by remember { mutableStateOf<TtPref?>(null) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(SPLASH_SCREEN) {
            SplashScreen()
            LaunchedEffect(key1 = "") {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (ttPref?.isTermsAgreed == true && ttPref?.isMinimumTopicSelected == true) {
                        currentNav.navigate(NEWSFEED_SCREEN) { launchSingleTop = true }
                    } else if (ttPref?.isTermsAgreed == true) {
                        currentNav.navigate(TOPIC_SELECT_SCREEN) { launchSingleTop = true }
                    } else {
                        currentNav.navigate(WELCOME_SCREEN) { launchSingleTop = true }
                    }
                }, 500L)
            }
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
        }
        composable(WELCOME_SCREEN) {
            WelcomeScreen()
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
        }
        composable(TOPIC_SELECT_SCREEN) {
            TopicSelectScreen()
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
        }
        composable(NEWSFEED_SCREEN) {
            NewsFeedScreen()
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
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