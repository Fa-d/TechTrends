package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.experimental.techtrends.R


@Composable
@Preview(showBackground = true)
fun FullArticleScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.cross_round), contentDescription = "",
            modifier = Modifier.padding(top = 30.dp)
        )

        Spacer(Modifier.height(40.dp))
        Text(
            "Monday March 10, 2025 at 10:40", style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        )
        Spacer(Modifier.height(10.dp))
        Text(
            "Weak and the ineffecetive", style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(Modifier.height(10.dp))

        Text(
            "Author name", style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        )
        Text(
            "Written in", style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        )

        Spacer(Modifier.height(25.dp))

        Text(
            "Descriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive TextDescriptive Text",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(Modifier.height(30.dp))
    }
}