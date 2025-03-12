package dev.experimental.techtrends.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.experimental.techtrends.nav.BottomNavigationBar
import dev.experimental.techtrends.nav.NavScreens.FAV_SCREEN
import dev.experimental.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import dev.experimental.techtrends.nav.NavScreens.PROFILE_SCREEN
import dev.experimental.techtrends.nav.NavScreens.SAVED_SCREEN
import dev.experimental.techtrends.nav.RMNavGraph
import dev.experimental.techtrends.ui.theme.TTTheme
import dev.experimental.techtrends.utils.LocalNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        actionBar?.hide()


        setContent {
            val navController: NavHostController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            val shouldDisplayBottomBar = when (currentDestination) {
                NEWSFEED_SCREEN, FAV_SCREEN, SAVED_SCREEN, PROFILE_SCREEN -> true
                else -> false
            }
            CompositionLocalProvider(LocalNavController provides navController) {
                TTTheme {
                    Scaffold(
                        bottomBar = {
                            if (shouldDisplayBottomBar) {
                                BottomNavigationBar(navController)
                            }
                        },
                        contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            RMNavGraph(navController)
                        }
                    }
                }
            }

        }
    }
}

