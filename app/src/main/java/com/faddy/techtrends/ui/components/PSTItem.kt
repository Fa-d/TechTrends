package com.faddy.techtrends.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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

data class PSTItemData(
    val icon: ImageVector, val title: String, val subtitle: String
)


@Composable
@Preview(showBackground = true)
fun PSTItem(
    pSTItemData: PSTItemData = PSTItemData(
        icon = Icons.Filled.AccountCircle, title = "Accounts", subtitle = "Your Logged In Accounts"
    ), onClick: () -> Unit = {}
) {

    Box(modifier = Modifier.clickable {
        onClick.invoke()
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            Icon(
                imageVector = pSTItemData.icon,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .scale(1.2f)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(pSTItemData.title, style = MaterialTheme.typography.bodyMedium)
                if (pSTItemData.subtitle.isNotEmpty()) {
                    Spacer(Modifier.height(5.dp))
                    Text(
                        pSTItemData.subtitle, style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }

}
