package dev.experimental.techtrends.ui.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dev.experimental.techtrends.models.CategoryModel
import dev.experimental.techtrends.nav.NavScreens.NEWSFEED_SCREEN
import dev.experimental.techtrends.ui.components.appBar
import dev.experimental.techtrends.ui.viewmodels.ChooseTopicViewModel
import dev.experimental.techtrends.utils.CenteredError
import dev.experimental.techtrends.utils.CenteredProgressbar
import dev.experimental.techtrends.utils.LocalNavController
import dev.experimental.techtrends.utils.UIState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopicSelectScreen() {
    val viewModel = hiltViewModel<ChooseTopicViewModel>()
    val typedText = remember { mutableStateOf("") }
    val categoryState = viewModel.allCategoriesList.collectAsState()
    val categoryCount = remember { mutableStateOf(0) }
    val categoryList = remember { mutableStateOf(listOf<CategoryModel>()) }
    val showPB = remember { mutableStateOf(false) }
    val showError = remember { mutableStateOf(false) }
    val errorMsg = remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.getAllCategories()
    }

    LaunchedEffect(categoryState.value) {
        when (categoryState.value) {
            is UIState.Error -> {
                showPB.value = false
                showError.value = true
                errorMsg.value = (categoryState.value as UIState.Error).message
            }

            is UIState.Loading -> {
                showPB.value = true
                showError.value = false
            }

            is UIState.Success -> {
                showPB.value = false
                showError.value = false
                categoryList.value = (categoryState.value as UIState.Success).data
                categoryCount.value = categoryList.value.count { it.selectedByUser == "user1" }
            }

            UIState.Idle -> {
                showPB.value = true
                showError.value = false
            }
        }
    }

    Box() {
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
                text = if (categoryCount.value < 5) "Choose up to ${5 - categoryCount.value} to get started"
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
                // StaggeredGridSelectableList(viewModel, typedText.value)
                FlowRow(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (item in categoryList.value) {
                        StaggeredGridItem(item, viewModel)
                    }
                }
            }
            NextButton(categoryCount.value, viewModel)
        }
        if (showPB.value) CenteredProgressbar()
        if (showError.value) {
            CenteredError(errorMsg.value)
        }
    }
}


@Composable
fun NextButton(categoryCount: Int, viewModel: ChooseTopicViewModel) {
    val currentNav = LocalNavController.current
    val worker = WorkManager.getInstance(LocalContext.current)
    val shouldShowDialog = remember { mutableStateOf(false) } // 1
    val workProgress = remember { mutableFloatStateOf(0f) } // 1

    if (shouldShowDialog.value) {
        SyncDataDialog(workProgress)
    }
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

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
                shouldShowDialog.value = true
                val workReqList = viewModel.startFeedFetching()
                worker.beginUniqueWork(
                    "fetchFeedsByCategory", ExistingWorkPolicy.REPLACE, workReqList
                ).enqueue()

                worker.getWorkInfosForUniqueWorkLiveData("fetchFeedsByCategory")
                    .observe(lifecycleOwner) { workInfos ->
                        val totalWork = workInfos.size
                        val doneWork = workInfos.count { it.state == WorkInfo.State.SUCCEEDED }
                        workProgress.floatValue = 1 - ((totalWork - doneWork).toFloat() / totalWork)
                        if (workInfos.all { it.state == WorkInfo.State.SUCCEEDED }) {
                            currentNav.navigate(NEWSFEED_SCREEN) { launchSingleTop = true }
                            viewModel.setTopicSelectedStatus()
                        }
                    }
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

@Composable
fun SyncDataDialog(workProgress: MutableState<Float>) {
    Dialog(
        onDismissRequest = {},
        content = {
            Card {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Syncing your data.",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 10.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Please give us a moment while we build your feed.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 10.dp),
                    )
                    LinearProgressIndicator(
                        progress = { workProgress.value },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),

                        )
                }
            }
        },
    )
}
