package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faddy.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import com.faddy.techtrends.ui.viewmodels.ChooseTopicViewModel
import com.faddy.techtrends.utils.LocalNavController

@Composable
@Preview(showSystemUi = true)
fun TopicSelectScreen() {
    Column(
        modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppBar()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "What are you interested in ?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Choose up to 5 to get started",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, false)
                .verticalScroll(rememberScrollState())
        ) {
            StaggeredGridSelectableList()
        }
        NextButton()
    }
}


@Composable
fun NextButton() {
    val currentNav = LocalNavController.current
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.1f),
                        Color.Transparent,
                    )
                )
            )
    ) {
        Button(
            onClick = {
                currentNav.navigate(NEWSFEED_SCREEN) { launchSingleTop = true }
            },
            Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StaggeredGridSelectableList() {
    val viewModel = hiltViewModel<ChooseTopicViewModel>()
    viewModel.getAllCategories()
    val categoryState = viewModel.allCategoriesList.collectAsState()

    val items: List<String> = categoryState.value.map { it -> it.name }.distinct()
        .sortedBy { it.length }
    val onItemSelected = {}

    FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        for (item in items) {
            StaggeredGridItem(item, onItemSelected)
        }
    }
}

@Composable
private fun StaggeredGridItem(item: String, onItemSelected: () -> Unit) {
    val isSelected = remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            isSelected.value = !isSelected.value
            onItemSelected()
        }, colors = if (isSelected.value) {
            ButtonDefaults.outlinedButtonColors(Color.Gray)
        } else {
            ButtonDefaults.outlinedButtonColors()
        }, modifier = Modifier.padding(3.dp)
    ) {
        Text(text = item, style = MaterialTheme.typography.bodyMedium)
    }
}

