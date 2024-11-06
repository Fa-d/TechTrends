package dev.experimental.techtrends.nav

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.experimental.techtrends.nav.NavScreens.FAV_SCREEN
import dev.experimental.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import dev.experimental.techtrends.nav.NavScreens.NEWS_DETAILS_SCREEN
import dev.experimental.techtrends.nav.NavScreens.PROFILE_SCREEN
import dev.experimental.techtrends.nav.NavScreens.SAVED_SCREEN
import dev.experimental.techtrends.nav.NavScreens.SPLASH_SCREEN
import dev.experimental.techtrends.nav.NavScreens.TOPIC_RESELECT_SCREEN
import dev.experimental.techtrends.nav.NavScreens.TOPIC_SELECT_SCREEN
import dev.experimental.techtrends.nav.NavScreens.WELCOME_SCREEN
import dev.experimental.techtrends.ui.screens.FavScreen
import dev.experimental.techtrends.ui.screens.NewsFeedScreen
import dev.experimental.techtrends.ui.screens.ProfileScreen
import dev.experimental.techtrends.ui.screens.SavedScreen
import dev.experimental.techtrends.ui.screens.SplashScreen
import dev.experimental.techtrends.ui.screens.TopicSelectScreen
import dev.experimental.techtrends.ui.screens.WelcomeScreen
import dev.experimental.techtrends.ui.viewmodels.LandingViewmodel
import dev.experimental.techtrends.utils.LocalNavController


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
                currentNav.navigate(FAV_SCREEN) { launchSingleTop = true }
            } else {
                TopicSelectScreen()
                BackHandler {
                    (currentNav.context as ComponentActivity).finish()
                }
            }

        }
        composable(TOPIC_RESELECT_SCREEN) {
            TopicSelectScreen()
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