package dev.experimental.techtrends.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.experimental.techtrends.R
import dev.experimental.techtrends.models.custom.FavCompanyItem

@Composable
@Preview(showBackground = true)
fun FavSourcesItem(
    favCompanyItem: FavCompanyItem = FavCompanyItem(
        companyDesc = "a random desc", companyName = "Google", articleTitle = "", companyLogo = ""
    )
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = favCompanyItem.companyLogo,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(Modifier.width(10.dp))
            Text(favCompanyItem.companyName)
            Spacer(Modifier.weight(1f))
            Image(painter = painterResource(R.drawable.lovefill), contentDescription = "")

        }
        Spacer(Modifier.height(10.dp))
        Text(favCompanyItem.companyDesc)
        Spacer(Modifier.height(10.dp))
        HorizontalDivider(
            modifier = Modifier.height(1.dp), color = Color(0XFFE9ECF0)
        )
        Spacer(Modifier.height(10.dp))

    }
}