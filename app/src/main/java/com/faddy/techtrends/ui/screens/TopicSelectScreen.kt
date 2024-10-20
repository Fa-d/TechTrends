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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.WorkManager
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.ui.viewmodels.ChooseTopicViewModel
import com.faddy.techtrends.utils.CenteredProgressbar
import com.faddy.techtrends.utils.LocalNavController

@Composable
@Preview(showSystemUi = true)
fun TopicSelectScreen() {
    val viewModel = hiltViewModel<ChooseTopicViewModel>()
    val typedText = remember { mutableStateOf("") }
    viewModel.getAllCategories()
    val categoryState = viewModel.allCategoriesList.collectAsState()
    val categoryCount = categoryState.value.count { it.selectedByUser == "user1" }

    Column(
        modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        typedText.value = appBar().value
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "What are you interested in ?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = if (categoryCount < 5) "Choose up to ${5 - categoryCount} to get started"
            else "You can now proceed to next page.",
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
            StaggeredGridSelectableList(viewModel, typedText.value)
        }
        NextButton(categoryCount, viewModel)
    }
}


@Composable
fun NextButton(categoryCount: Int, viewModel: ChooseTopicViewModel) {
    val currentNav = LocalNavController.current
    val worker = WorkManager.getInstance(LocalContext.current)

    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Black.copy(alpha = 0.1f), Color.Transparent)
                )
            )
    ) {
        Button(

            onClick = {
                // currentNav.navigate(NEWSFEED_SCREEN) { launchSingleTop = true }
                viewModel.startChildFeedFetching(worker)
            },
            Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            enabled = categoryCount >= 5
        ) {
            Text("Next")
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StaggeredGridSelectableList(viewModel: ChooseTopicViewModel, searchedItem: String) {

    val items: List<CategoryModel> = if (searchedItem.isNotEmpty()) {
        viewModel.allCategoriesList.collectAsState().value.filter {
            it.name!!.contains(
                searchedItem,
                ignoreCase = true
            )
        }
    } else {
        viewModel.allCategoriesList.collectAsState().value
    }
    if (items.isEmpty()) CenteredProgressbar() else FlowRow(
        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (item in items) {
            StaggeredGridItem(item, viewModel)
        }
    }
}

@Composable
private fun StaggeredGridItem(item: CategoryModel, viewModel: ChooseTopicViewModel) {
    val isSelected = remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            isSelected.value = !isSelected.value
            viewModel.setSelectedCategoryByUser(item.id)
        }, colors = if (item.selectedByUser == "user1") {
            ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.secondaryContainer)
        } else {
            ButtonDefaults.outlinedButtonColors()
        }, modifier = Modifier.padding(3.dp)
    ) {
        Text(
            text = item.name!!,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

