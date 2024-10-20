package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faddy.techtrends.R

@Composable
@Preview(showSystemUi = true)
fun appBar(): MutableState<String> {
    val typedText = remember { mutableStateOf("") }
    val selected = remember { mutableStateOf(true) }

    Box(modifier = Modifier.shadow(elevation = 10.dp, spotColor = Color.Transparent)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                painter = painterResource(R.drawable.logoround),
                contentDescription = ""
            )

            if (selected.value) Spacer(modifier = Modifier.weight(1f))
            else OutlinedTextField(
                value = typedText.value,
                shape = RoundedCornerShape(14.dp),
                onValueChange = { typedText.value = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )

            Icon(imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .scale(1.2f)
                    .clickable {
                        selected.value = !selected.value
                    })
        }
    }

    return typedText
}