package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.experimental.techtrends.models.custom.FavCompanyItem
import dev.experimental.techtrends.ui.components.FavCategoryItem
import dev.experimental.techtrends.ui.components.FavSourcesItem
import dev.experimental.techtrends.ui.components.appBar
import dev.experimental.techtrends.ui.theme.tabTypography
import dev.experimental.techtrends.ui.viewmodels.FavViewModel
import dev.experimental.techtrends.ui.viewmodels.UIState
import dev.experimental.techtrends.utils.CenteredProgressbar


@Composable
fun FavScreen() {
    val viewmodel = hiltViewModel<FavViewModel>()
    val itemList = viewmodel.favList.collectAsState()
    var favList = remember { mutableStateOf(listOf<FavCompanyItem>()) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val showPB = remember { mutableStateOf<Boolean>(false) }

    LaunchedEffect(null) {
        viewmodel.getAllFavFeeds()
    }

    LaunchedEffect(itemList.value) {
        when (itemList.value) {
            is UIState.Error -> {
                showPB.value = false
            }

            UIState.Idle -> {
                showPB.value = false
            }

            UIState.Loading -> {
                showPB.value = true
            }

            is UIState.Success<List<FavCompanyItem>> -> {
                showPB.value = false
                favList.value = (itemList.value as UIState.Success<List<FavCompanyItem>>).data
            }
        }
    }
    if (showPB.value) {
        CenteredProgressbar()
    }
    Column {
        appBar(showSearch = false)
        TabRow(
            selectedTabIndex = selectedTabIndex, contentColor = Color.Gray, tabs = {
                Tab(
                    selected = true,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("Categories", style = tabTypography.titleMedium) })
                Tab(
                    selected = true,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Sources", style = tabTypography.titleMedium) })
            })

        LazyColumn {
            if (favList.value.isNotEmpty()) {
                items(favList.value.size) { index ->
                    if (selectedTabIndex == 0) {
                        FavCategoryItem(
                            favList.value[index],
                            onFavClicked = {
                                viewmodel.removeItemFromFav(id = favList.value[index].itemId)
                            },
                        )
                    } else if (selectedTabIndex == 1) {
                        FavSourcesItem(
                            favList.value[index],
                            onFavClicked = {
                                viewmodel.removeItemFromFav(id = favList.value[index].itemId)
                            },
                        )
                    }
                }
            } else {
                item {
                    Text(
                        "No items found",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 50.dp)
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,

                        )
                }
            }

        }
    }
}