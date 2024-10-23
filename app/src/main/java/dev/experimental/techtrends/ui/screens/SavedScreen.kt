package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.experimental.techtrends.ui.components.appBar


@Composable
@Preview
fun SavedScreen() {
    Column {
        appBar()
        Text(text = "Saved Screen")
    }
}