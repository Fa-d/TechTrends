package com.faddy.techtrends.ui

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
import com.faddy.techtrends.nav.BottomNavigationBar
import com.faddy.techtrends.nav.NavScreens.FAV_SCREEN
import com.faddy.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import com.faddy.techtrends.nav.NavScreens.PROFILE_SCREEN
import com.faddy.techtrends.nav.NavScreens.SAVED_SCREEN
import com.faddy.techtrends.nav.RMNavGraph
import com.faddy.techtrends.ui.theme.TTTheme
import com.faddy.techtrends.utils.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        actionBar?.hide()


        setContent {
            val navController: NavHostController = rememberNavController()
            CompositionLocalProvider(LocalNavController provides navController) {
                TTTheme {
                    Scaffold(
                        bottomBar = {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination?.route
                            val shouldDisplayBottomBar = when (currentDestination) {
                                NEWSFEED_SCREEN, FAV_SCREEN, SAVED_SCREEN, PROFILE_SCREEN -> true
                                else -> false
                            }
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

