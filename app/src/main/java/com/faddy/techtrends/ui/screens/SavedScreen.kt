package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.faddy.techtrends.ui.components.appBar


@Composable
@Preview
fun SavedScreen() {
    Column {
        appBar()
        Text(text = "Saved Screen")
    }
}