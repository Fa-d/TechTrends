package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.faddy.techtrends.R
import com.faddy.techtrends.models.FeedItem
import com.faddy.techtrends.ui.components.appBar
import com.faddy.techtrends.ui.viewmodels.NewsFeedViewModel
import com.faddy.techtrends.utils.CenteredProgressbar
import com.faddy.techtrends.utils.getHtmlFormattedString
import kotlinx.coroutines.launch

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
            ContentByTab(tabItem.value[selectedTabIndex].name ?: "", viewModel)
        }
    }
}


@Composable
fun ContentByTab(pageName: String, viewModel: NewsFeedViewModel) {
    LaunchedEffect(pageName) {
        viewModel.getAllFeedByCategory(pageName)
    }
    val response = viewModel.feedItemByCategory.collectAsState()

    if (response.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                "No articles currently available for this category.",
                style = MaterialTheme.typography.titleMedium
            )
        }
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 20.dp)
        ) {
            for (item in response.value) {
                ContentItem(item)
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun ContentItem(feedItem: FeedItem = FeedItem(id = 0)) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val coroutineScope = rememberCoroutineScope()
    val clickState = remember { mutableStateOf(false) }
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(
                model = feedItem.companyLogoUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)

            )

            Spacer(modifier = Modifier.width(15.dp))
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

            Icon(painter = painterResource(R.drawable.three_dots),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable {
                        clickState.value = true
                    })
            if (clickState.value) {
                ModalBottomSheet(onDismissRequest = { clickState.value = false },
                    sheetState = bottomSheetState,
                    content = {
                        NewsBottomSheet(feedItem, onDismissed = {
                            coroutineScope.launch {
                                bottomSheetState.hide()
                                clickState.value = false
                            }
                        })
                    })
            }

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
                text = feedItem.feedContent?.substring(0, 100)?.getHtmlFormattedString() ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

