package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.faddy.techtrends.R
import com.faddy.techtrends.nav.NavScreens.TOPIC_SELECT_SCREEN
import com.faddy.techtrends.ui.viewmodels.LandingViewmodel
import com.faddy.techtrends.utils.ExpandableText
import com.faddy.techtrends.utils.LocalNavController
import com.faddy.techtrends.utils.getHtmlFormattedString

@Composable
@Preview(showSystemUi = true)
fun WelcomeScreen() {

    val currentNav = LocalNavController.current
    val viewModel: LandingViewmodel = hiltViewModel()
    var isChecked by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        Text(
            modifier = Modifier.padding(top = 40.dp, start = 16.dp),
            text = "Welcome", style = MaterialTheme.typography.titleLarge,
        )
        Loader()

        Text(
            modifier = Modifier.padding(top = 40.dp, start = 16.dp),
            text = "Terms & conditions",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp, start = 16.dp)
                .wrapContentHeight()
        ) {
            Checkbox(
                checked = isChecked, onCheckedChange = { checked ->
                    isChecked = checked
                    viewModel.setTermsConditionChecked(checked)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text = stringResource(R.string.terms_and_conditions),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        ExpandableText(
            text = stringResource(R.string.tos_content).getHtmlFormattedString(),
            modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp),
            minimizedMaxLines = 9
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            FloatingActionButton(
                onClick = {
                    currentNav.navigate(TOPIC_SELECT_SCREEN) { launchSingleTop = true }
                },
                shape = CircleShape,
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .padding(end = 16.dp, bottom = 40.dp, top = 20.dp),
            ) { Icon(Icons.AutoMirrored.Filled.ArrowForward, "ForwardArrow") }
        }

    }


}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    LottieAnimation(
        modifier = Modifier
            .height(250.dp)
            .padding(top = 20.dp),
        composition = composition,
        progress = { progress },
    )
}
