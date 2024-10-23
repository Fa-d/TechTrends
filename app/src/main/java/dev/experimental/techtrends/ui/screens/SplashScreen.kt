package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.experimental.techtrends.R
import dev.experimental.techtrends.nav.NavScreens.WELCOME_SCREEN
import dev.experimental.techtrends.ui.theme.splashBack
import dev.experimental.techtrends.utils.LocalNavController
import kotlinx.coroutines.delay


@Composable
@Preview(showSystemUi = true)
fun SplashScreen() {
    val currentNav = LocalNavController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = splashBack)
    ) {
        Image(

            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 150.dp, height = 150.dp),
            painter = painterResource(R.drawable.logomark),
            contentDescription = ""
        )
    }
    LaunchedEffect(key1 = true) {
        delay(500)
        currentNav.navigate(WELCOME_SCREEN) { launchSingleTop = true }
    }

}