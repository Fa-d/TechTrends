package dev.experimental.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.experimental.techtrends.ui.components.PSTItem
import dev.experimental.techtrends.ui.components.PSTItemData
import dev.experimental.techtrends.ui.components.appBar


@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        appBar(false)
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.AccountCircle,
                title = "Accounts",
                subtitle = "Your Logged In Accounts"
            )
        )

        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.Edit,
                title = "Languages",
                subtitle = "English, Spanish, French, German and more.."
            )
        )

        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.FavoriteBorder,
                title = "Interested Topics",
                subtitle = "Add/Remove your favorite topics."
            )
        )

        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.Star,
                title = "Send FeedBack",
                subtitle = "Help Us Improve suggesting feedback about app."
            )
        )

        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.Share,
                title = "Share",
                subtitle = "Let your friends know about this app"
            )
        )

        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.Refresh,
                title = "History",
                subtitle = "Check you recent read articles"
            )
        )

        PSTItem(
            PSTItemData(
                icon = Icons.Outlined.ExitToApp, title = "Logout", subtitle = ""
            )
        )
    }
}
