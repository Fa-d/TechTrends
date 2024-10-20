package com.faddy.techtrends.nav

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.faddy.techtrends.ui.viewmodels.LandingViewmodel
import com.faddy.techtrends.utils.LocalNavController


@Composable
fun RMNavGraph(
    navController: NavHostController, startDestination: String = SPLASH_SCREEN
) {
    val currentNav = LocalNavController.current
    val viewModel: LandingViewmodel = hiltViewModel()
    val termState = viewModel.getIsTermsConditionChecked().collectAsState(false)
    val minimumTopic = viewModel.getIsMinimumTopicSelected().collectAsState(false)
    NavHost(navController = navController, startDestination = startDestination) {
        composable(SPLASH_SCREEN) {
            SplashScreen()
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
        }
        composable(WELCOME_SCREEN) {
            if (termState.value) {
                currentNav.navigate(TOPIC_SELECT_SCREEN) { launchSingleTop = true }
            } else {
                WelcomeScreen()
                BackHandler {
                    (currentNav.context as ComponentActivity).finish()
                }
            }
        }
        composable(TOPIC_SELECT_SCREEN) {
            if (minimumTopic.value) {
                currentNav.navigate(NEWSFEED_SCREEN) { launchSingleTop = true }
            } else {
                TopicSelectScreen()
                BackHandler {
                    (currentNav.context as ComponentActivity).finish()
                }
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