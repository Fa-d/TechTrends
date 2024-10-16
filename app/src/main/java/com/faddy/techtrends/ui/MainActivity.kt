package com.faddy.techtrends.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faddy.techtrends.core.MainViewModel
import com.faddy.techtrends.nav.RMNavGraph
import com.faddy.techtrends.ui.theme.TTTheme
import com.faddy.techtrends.utils.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        actionBar?.hide()


        setContent {
            val navController: NavHostController = rememberNavController()
            CompositionLocalProvider(LocalNavController provides navController) {
                TTTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        RMNavGraph(navController)
                    }
                }
            }

        }
    }
}

