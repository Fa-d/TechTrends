package com.faddy.techtrends.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ProfileItemData(
    val icon: ImageVector, val title: String, val subtitle: String
)

@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    Column {
        appBar(false)
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        ProfileItems(
            ProfileItemData(
                icon = Icons.Filled.AccountCircle,
                title = "Accounts",
                subtitle = "Your Logged In Accounts"
            )
        )

        ProfileItems(
            ProfileItemData(
                icon = Icons.Outlined.Edit,
                title = "Languages",
                subtitle = "English, Spanish, French, German and more.."
            )
        )

        ProfileItems(
            ProfileItemData(
                icon = Icons.Outlined.FavoriteBorder,
                title = "Interested Topics",
                subtitle = "Add/Remove your favorite topics."
            )
        )

        ProfileItems(
            ProfileItemData(
                icon = Icons.Filled.Star,
                title = "Send FeedBack",
                subtitle = "Help Us Improve suggesting feedback about app."
            )
        )

        ProfileItems(
            ProfileItemData(
                icon = Icons.Filled.Share,
                title = "Share",
                subtitle = "Let your friends know about this app"
            )
        )

        ProfileItems(
            ProfileItemData(
                icon = Icons.Outlined.ExitToApp, title = "Logout", subtitle = ""
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileItems(
    profileItemData: ProfileItemData = ProfileItemData(
        icon = Icons.Filled.AccountCircle, title = "Accounts", subtitle = "Your Logged In Accounts"
    )
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = profileItemData.icon,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .scale(1.2f)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(profileItemData.title, style = MaterialTheme.typography.titleMedium)
            if (profileItemData.subtitle.isNotEmpty()) {
                Spacer(Modifier.height(5.dp))
                Text(
                    profileItemData.subtitle, style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
