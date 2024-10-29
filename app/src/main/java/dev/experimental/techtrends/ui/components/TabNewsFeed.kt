package dev.experimental.techtrends.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.experimental.techtrends.ui.viewmodels.NewsFeedViewModel

@Composable
fun TabNewsFeed(pageName: String, viewModel: NewsFeedViewModel) {
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
                FeedContentItem(item)
            }
        }
    }
}