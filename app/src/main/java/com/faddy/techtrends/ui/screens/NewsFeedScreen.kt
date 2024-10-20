package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter

import com.faddy.techtrends.models.FeedChildItem
import com.faddy.techtrends.ui.viewmodels.ChooseTopicViewModel
import com.faddy.techtrends.ui.viewmodels.MainViewModel
import com.faddy.techtrends.utils.CenteredProgressbar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsFeedScreen() {
    Column {
        appBar()
        TabRowCom()
    }
}


@ExperimentalFoundationApi
@Composable
fun TabRowCom() {
    val viewModel = hiltViewModel<ChooseTopicViewModel>()
    viewModel.getAllCategories()
    val tabItem = viewModel.allCategoriesList.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }


    if (tabItem.value.isEmpty()) CenteredProgressbar()
    else Column(modifier = Modifier.fillMaxWidth()) {
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
            tabItem.value.distinct().forEachIndexed { index, tabItem ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(text = tabItem.name?:"")
                    },
                )
            }
        }
        ContentByTab(tabItem.value[selectedTabIndex].name?:"")
    }
}


@Composable
fun ContentByTab(pageName: String) {
    val viewModel = hiltViewModel<MainViewModel>()
    val response = remember { mutableStateOf<List<FeedChildItem>>(listOf()) }

    LaunchedEffect(key1 = pageName) {
        response.value = viewModel.getAllFeedChildByCategory(pageName)
    }
    if (response.value.isEmpty()) CenteredProgressbar() else Column(
        modifier = Modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        for (item in response.value) {
            ContentItem(item)
        }
    }

}


//@Preview(showSystemUi = false, showBackground = true)
@Composable
fun ContentItem(feedItem: FeedChildItem) {
    // val feedItem = FeedItem("Feed Topic", "Feed Url", 1, "Link", "Title")
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(
                model = feedItem.companyLogoUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(CircleShape)

            )

            Spacer(modifier = Modifier.width(30.dp))
            Column {
                Text(feedItem.companyName, style = MaterialTheme.typography.bodyLarge)
                Text(feedItem.datePosted, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = rememberAsyncImagePainter(feedItem.feedArticleUrl),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(50.dp)
                    .width(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(
                feedItem.feedTitle,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )
            Text(
                feedItem?.feedContent?.substring(0, 100) ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

