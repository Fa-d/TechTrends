package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faddy.techtrends.ui.components.CompanyUI
import com.faddy.techtrends.ui.components.appBar
import com.faddy.techtrends.ui.viewmodels.FavViewModel
import com.faddy.techtrends.utils.CenteredProgressbar


@Composable
fun FavScreen() {
    val state = rememberLazyStaggeredGridState()
    val viewmodel = hiltViewModel<FavViewModel>()
    val itemList = viewmodel.favList.collectAsState()

    LaunchedEffect(null) {
        viewmodel.getAllFeed()
    }
    if (itemList.value.isEmpty()) {
        CenteredProgressbar()
    }
    Column {
        appBar()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(1),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            state = state,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            if (itemList.value.isNotEmpty()) {
                itemList.value.forEach {
                    item {
                        CompanyUI(it)
                    }
                }
            }
        }

    }
}