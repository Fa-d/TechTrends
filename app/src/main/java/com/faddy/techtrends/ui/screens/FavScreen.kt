package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun FavScreen() {
    Column {
        appBar()
        Text(text = "Fav Screen")
    }
}