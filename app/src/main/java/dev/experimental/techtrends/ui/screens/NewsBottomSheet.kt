package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.experimental.techtrends.R
import dev.experimental.techtrends.models.FeedItem
import dev.experimental.techtrends.ui.components.PSTItem
import dev.experimental.techtrends.ui.components.PSTItemData
import dev.experimental.techtrends.ui.viewmodels.NewsFeedViewModel

@Composable
@Preview(showBackground = true)
fun NewsBottomSheet(feedItem: FeedItem = FeedItem(id = 0), onDismissed: () -> Unit = {}) {
    val viewModel = hiltViewModel<NewsFeedViewModel>()
    Column {
        PSTItem(
            PSTItemData(
                icon = ImageVector.vectorResource(id = R.drawable.save),
                title = "Save to read later",
                subtitle = ""
            ), onClick = {
                viewModel.setArticleLater(feedItem.id)
                onDismissed.invoke()
            }
        )
        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.Share, title = "Share via", subtitle = ""
            ), onClick = {
                onDismissed()
            }
        )

        PSTItem(
            PSTItemData(
                icon = ImageVector.vectorResource(id = R.drawable.message),
                title = "View ${feedItem.companyName} Posts",
                subtitle = ""
            ), onClick = {
                viewModel.setCompanyFav(id = feedItem.id)
                onDismissed.invoke()
            }
        )

        PSTItem(
            PSTItemData(
                icon = ImageVector.vectorResource(id = R.drawable.cross_round),
                title = "Unfollow ${feedItem.companyName}",
                subtitle = ""
            ), onClick = {
                viewModel.removeCategoryFromSelected(feedItem.categoryName)
                onDismissed.invoke()
            }
        )
    }

}