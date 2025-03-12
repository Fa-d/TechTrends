package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.experimental.techtrends.R
import dev.experimental.techtrends.ui.viewmodels.FullArticleVM
import dev.experimental.techtrends.utils.CenteredError


@Composable
@Preview(showBackground = true)
fun FullArticleScreen(id: String = "") {

    val fullArticleVM = hiltViewModel<FullArticleVM>()
    val feedState = fullArticleVM.feedItem.collectAsState()

    LaunchedEffect(Unit) {
        fullArticleVM.getFeedById(id)
    }

    Box() {
        if (feedState.value != null) {
            CenteredError("No Item with this ID found.")
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.cross_round),
                    contentDescription = "",
                    modifier = Modifier.padding(top = 30.dp)
                )

                Spacer(Modifier.height(40.dp))
                Text(
                    feedState.value?.datePosted ?: "", style = TextStyle(
                        fontSize = 12.sp, fontWeight = FontWeight.Light
                    )
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    feedState.value?.feedTitle ?: "", style = TextStyle(
                        fontSize = 22.sp, fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(Modifier.height(10.dp))

                Text(
                    feedState.value?.feedAuthor ?: "", style = TextStyle(
                        fontSize = 12.sp, fontWeight = FontWeight.Light
                    )
                )
                Text(
                    feedState.value?.companyName ?: "", style = TextStyle(
                        fontSize = 12.sp, fontWeight = FontWeight.Light
                    )
                )

                Spacer(Modifier.height(25.dp))

                Text(
                    feedState.value?.feedContent ?: "",
                    style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.Normal
                    )
                )
                Spacer(Modifier.height(30.dp))
            }

        }
    }
}