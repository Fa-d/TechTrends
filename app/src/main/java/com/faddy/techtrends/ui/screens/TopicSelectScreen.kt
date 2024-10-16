package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faddy.techtrends.R

@Composable
@Preview(showSystemUi = true)
fun TopicSelectScreen() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                painter = painterResource(R.drawable.round_logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Filled.Search, "Search", modifier = Modifier.scale(1.2f)
            )
        }
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 20.dp))

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
        StaggeredGridOfOutlinedSelectableButtons()
    }
}


@Composable
fun StaggeredGridOfOutlinedSelectableButtons() {
    val items: List<String> = listOf("456789", "1", "2345", "23", "34567", "456789", "5678765432")
    val onItemSelected = {}
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(5.dp),
        verticalItemSpacing = 30.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            StaggeredGridItem(item, onItemSelected)
        }
    }
}

@Composable
private fun StaggeredGridItem(item: String, onItemSelected: () -> Unit) {
    val isSelected = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedButton(
            onClick = {
                isSelected.value = !isSelected.value
                onItemSelected()
            },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = if (isSelected.value) {
                ButtonDefaults.outlinedButtonColors()
            } else {
                ButtonDefaults.outlinedButtonColors()
            }
        ) {
            Text(text = item, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

