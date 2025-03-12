package dev.experimental.techtrends.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import dev.experimental.techtrends.R
import dev.experimental.techtrends.models.FeedItem
import dev.experimental.techtrends.nav.NavScreens.FULL_ARTICLE_SCREEN
import dev.experimental.techtrends.ui.screens.NewsBottomSheet
import dev.experimental.techtrends.ui.theme.FeedContentTypography
import dev.experimental.techtrends.ui.theme.HomeTypography
import dev.experimental.techtrends.ui.viewmodels.NewsFeedViewModel
import dev.experimental.techtrends.utils.LocalNavController
import dev.experimental.techtrends.utils.getHtmlFormattedString
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun FeedContentItem(
    feedItem: FeedItem = FeedItem(
        id = 0,
        companyName = "Company name",
        feedContent = "The 1921 Centre vs. Harvard football game was a regular-season collegiate American football game played on October 29, 1921, at Harvard Stadium in Boston, Massachusetts. The contest featured the undefeated Centre Praying Colonels, representing Centre College, and the undefeated Harvard Crimson, representing Harvard University. Centre entered the game as heavy underdogs, as Harvard had received 3-to-1 odds to win prior to kickoff. The only score of the game came less than two minutes into the third quarter when Centre quarterback Bo McMillin rushed for a touchdown. The conversion failed but the Colonels' defense held for the remainder of the game, and Centre won the game 6–0. The game is widely viewed as one of the largest upsets in college football history. It is often referred to by the shorthand \"C6H0\"; this originated shortly after the game when a Centre professor remarked that Harvard had been poisoned by this \"impossible\" chemical formula. ",
        datePosted = "20-04-2024",
        companyLogoUrl = "https://en.wikipedia.org/static/images/icons/wikipedia.png"
    )
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val coroutineScope = rememberCoroutineScope()
    val clickState = remember { mutableStateOf(false) }
    val viewModel = hiltViewModel<NewsFeedViewModel>()
    val navController = LocalNavController.current
    Column(modifier = Modifier.clickable() {
        navController.navigate(FULL_ARTICLE_SCREEN + "/${feedItem.id}")
    }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(
                model = feedItem.companyLogoUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)

            )

            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(feedItem.companyName, style = HomeTypography.titleMedium)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    formatDateRelative(feedItem.datePosted), style = HomeTypography.titleSmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = rememberAsyncImagePainter(feedItem.feedArticleUrl),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(50.dp)
                    .width(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Icon(painter = painterResource(R.drawable.three_dots),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable {
                        clickState.value = true
                    })
            if (clickState.value) {
                ModalBottomSheet(onDismissRequest = { clickState.value = false },
                    sheetState = bottomSheetState,
                    content = {
                        NewsBottomSheet(feedItem, onDismissed = {
                            coroutineScope.launch {
                                bottomSheetState.hide()
                                clickState.value = false
                            }
                        })
                    })
            }

        }
        Spacer(modifier = Modifier.height(5.dp))
        Column {
            Text(
                text = feedItem.feedTitle, style = FeedContentTypography.titleMedium,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = feedItem.feedContent?.substring(0, 100)?.getHtmlFormattedString() ?: "",
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                style = HomeTypography.titleSmall,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(
                if (feedItem.isFav == "user1") R.drawable.lovefill else R.drawable.love
            ), contentDescription = "", modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) {
                viewModel.setFeedAsFav(id = feedItem.id, feedCategory = feedItem.categoryName)
            })
            Spacer(Modifier.width(20.dp))
            Image(painter = painterResource(
                if (feedItem.isAlertOn == "user1") R.drawable.bellfill else R.drawable.bell
            ), contentDescription = "", modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) {
                viewModel.setFeedAlert(id = feedItem.id, feedCategory = feedItem.categoryName)
            })
            Spacer(Modifier.width(15.dp))
            /*
             Image(painter = painterResource(R.drawable.message), contentDescription = "")
             Spacer(Modifier.width(10.dp))
               BasicTextField(value = "",
                 onValueChange = {},
                 modifier = Modifier.fillMaxWidth(),
                 decorationBox = {
                     Text(text = "Write a note... ", style = HomeTypography.titleSmall)
                 })*/
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .padding(horizontal = 16.dp), color = Color(0XFFE9ECF0)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

fun formatDateRelative(iso8601String: String): String {
    val instant = Instant.parse(iso8601String)
    val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
    val now = LocalDateTime.now()
    val diffInDays = ChronoUnit.DAYS.between(localDateTime, now)
    val diffInWeeks = ChronoUnit.WEEKS.between(localDateTime, now)
    val diffInMonths = ChronoUnit.MONTHS.between(localDateTime, now)
    val diffInYears = ChronoUnit.YEARS.between(localDateTime, now)

    val formatter = DateTimeFormatter.ofPattern("hh:mm a")

    return when {
        diffInYears >= 2 -> "${formatter.format(localDateTime)}  2 years ago"
        diffInYears.toInt() == 1 -> "${formatter.format(localDateTime)}  1 year ago"
        diffInMonths >= 3 -> "${formatter.format(localDateTime)}  3 months ago"
        diffInMonths >= 1 -> "${formatter.format(localDateTime)}  1 month ago"
        diffInWeeks >= 4 -> "${formatter.format(localDateTime)}  4 weeks ago"
        diffInWeeks >= 1 -> "${formatter.format(localDateTime)}  1 week ago"
        diffInDays >= 2 -> "${formatter.format(localDateTime)}  ${diffInDays} days ago"
        diffInDays.toInt() == 1 -> "${formatter.format(localDateTime)}  Yesterday"
        else -> "${formatter.format(localDateTime)}  Today"
    }
}