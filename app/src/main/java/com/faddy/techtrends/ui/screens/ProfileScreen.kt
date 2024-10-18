package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ProfileScreen() {
    Column {
        AppBar()
        Text(text = "Profile Screen")
    }
}
