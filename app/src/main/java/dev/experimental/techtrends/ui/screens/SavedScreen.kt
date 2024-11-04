package dev.experimental.techtrends.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.experimental.techtrends.ui.components.SavedItemUI
import dev.experimental.techtrends.ui.components.appBar
import dev.experimental.techtrends.ui.viewmodels.SavedItemViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun SavedScreen() {
    val state = rememberLazyListState()
    val savedItemViewModel = hiltViewModel<SavedItemViewModel>()
    val itemList = savedItemViewModel.savedList.collectAsState()

    var unread by remember { mutableStateOf(false) }
    val dismissState = rememberDismissState(confirmStateChange = {
        if (it == DismissValue.DismissedToEnd) unread = !unread
        it != DismissValue.DismissedToEnd
    })
    Column {
        appBar()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            state = state,
        ) {

            if (itemList.value.isNotEmpty()) {
                items(itemList.value.size) { index: Int ->
                    var unread by remember { mutableStateOf(false) }
                    val dismissState = rememberDismissState(confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd) unread = !unread
                        it != DismissValue.DismissedToEnd
                    })
                    SwipeToDismiss(state = dismissState,
                        modifier = Modifier.padding(vertical = 4.dp),
                        directions = setOf(
                            DismissDirection.StartToEnd, DismissDirection.EndToStart
                        ),
                        background = {
                            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.LightGray
                                    DismissValue.DismissedToEnd -> Color.Green
                                    DismissValue.DismissedToStart -> Color.Red
                                }, label = ""
                            )

                            val alignment = when (direction) {
                                DismissDirection.StartToEnd -> Alignment.CenterStart
                                DismissDirection.EndToStart -> Alignment.CenterEnd
                            }
                            val icon = when (direction) {
                                DismissDirection.StartToEnd -> Icons.Default.Done
                                DismissDirection.EndToStart -> Icons.Default.Delete
                            }
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f,
                                label = ""
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp), contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.scale(scale)
                                )
                            }

                        },
                        dismissContent = {
                            SavedItemUI(itemList.value[index])
                        })
                }
            }
        }
    }
}