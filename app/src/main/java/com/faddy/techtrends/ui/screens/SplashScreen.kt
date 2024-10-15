package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faddy.techtrends.R
import com.faddy.techtrends.ui.theme.splashBack


@Composable
@Preview(showSystemUi = true)
fun SplashScreen() {

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

}