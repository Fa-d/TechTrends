package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import dev.experimental.techtrends.ui.components.FavCategoryItem
import dev.experimental.techtrends.ui.components.FavSourcesItem
import dev.experimental.techtrends.ui.components.appBar
import dev.experimental.techtrends.ui.viewmodels.FavViewModel
import dev.experimental.techtrends.utils.CenteredProgressbar


@Composable
fun FavScreen() {
    val viewmodel = hiltViewModel<FavViewModel>()
    val itemList = viewmodel.favList.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(null) {
        viewmodel.getAllFavFeeds()
    }
    if (itemList.value.isEmpty()) {
        CenteredProgressbar()
    }
    Column {
        appBar(showSearch = false)
        TabRow(selectedTabIndex = selectedTabIndex, tabs = {
            Tab(selected = true, onClick = { selectedTabIndex = 0 }, text = { Text("Categories") })
            Tab(selected = true, onClick = { selectedTabIndex = 1 }, text = { Text("Sources") })
        })
        LazyColumn {
            items(itemList.value.size) { index ->
                if (selectedTabIndex == 0) {
                    FavCategoryItem(itemList.value[index])
                } else if (selectedTabIndex == 1) {
                    FavSourcesItem(itemList.value[index])
                }
            }
        }
    }
}