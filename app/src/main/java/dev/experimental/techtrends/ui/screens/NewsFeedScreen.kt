package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.experimental.techtrends.ui.components.TabNewsFeed
import dev.experimental.techtrends.ui.components.appBar
import dev.experimental.techtrends.ui.viewmodels.NewsFeedViewModel
import dev.experimental.techtrends.utils.CenteredProgressbar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsFeedScreen() {
    Column {
        appBar()
        NewsFeedContent()
    }
}


@ExperimentalFoundationApi
@Composable
fun NewsFeedContent() {
    val viewModel = hiltViewModel<NewsFeedViewModel>()
    viewModel.getAllCategoriesData()
    val tabItem = viewModel.allCategoriesListByUser.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }


    if (tabItem.value.isEmpty()) {
        CenteredProgressbar()
    } else {
        Column(modifier = Modifier.fillMaxWidth()) {
            ScrollableTabRow(selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                contentColor = Color.Gray,
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .fillMaxWidth()
                    )
                }) {
                tabItem.value.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(text = tabItem.name ?: "")
                        },
                    )
                }
            }
            TabNewsFeed(tabItem.value[selectedTabIndex].name ?: "", viewModel)
        }
    }
}





