package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faddy.techtrends.R
import com.faddy.techtrends.ui.fragments.chooseTopic.ChooseTopicViewModel

@Composable
@Preview(showSystemUi = true)
fun TopicSelectScreen() {

    Column(
        modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
    ) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, false)
                .verticalScroll(rememberScrollState())
        ) {
            StaggeredGridSelectableList()
        }
        Button(
            onClick = {},
            Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StaggeredGridSelectableList() {
    val viewModel = hiltViewModel<ChooseTopicViewModel>()
    viewModel.getAllCategories()
    val categoryState = viewModel.allCategoriesList.collectAsState()
    val catList = categoryState.value.map { it -> it.name }.distinct().sortedBy { it.length }
    val items: List<String> = catList
    val onItemSelected = {}

    FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        for (item in items) {
            StaggeredGridItem(item, onItemSelected)
        }
    }
}

@Composable
private fun StaggeredGridItem(item: String, onItemSelected: () -> Unit) {
    val isSelected = remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            isSelected.value = !isSelected.value
            onItemSelected()
        }, colors = if (isSelected.value) {
            ButtonDefaults.outlinedButtonColors(Color.Gray)
        } else {
            ButtonDefaults.outlinedButtonColors()
        }, modifier = Modifier.padding(3.dp)
    ) {
        Text(text = item, style = MaterialTheme.typography.bodyMedium)
    }
}

