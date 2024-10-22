package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.faddy.techtrends.R
import com.faddy.techtrends.models.FeedChildItem

@Composable
@Preview(showBackground = true)
fun NewsBottomSheet(feedItem: FeedChildItem = FeedChildItem(id = 0)) {
    Column {
        PSTItem(
            PSTItemData(
                icon = ImageVector.vectorResource(id = R.drawable.save),
                title = "Save to read later",
                subtitle = ""
            )
        )
        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.Share, title = "Share via", subtitle = ""
            )
        )

        PSTItem(
            PSTItemData(
                icon = ImageVector.vectorResource(id = R.drawable.message),
                title = "View ${feedItem.companyName} Posts",
                subtitle = ""
            )
        )

        PSTItem(
            PSTItemData(
                icon = ImageVector.vectorResource(id = R.drawable.cross_round),
                title = "Unfollow ${feedItem.companyName}",
                subtitle = ""
            )
        )
    }

}