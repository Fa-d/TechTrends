package dev.experimental.techtrends.utils

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import dev.experimental.techtrends.models.FeedItem
import dev.experimental.techtrends.models.custom.FavCompanyItem
import dev.experimental.techtrends.ui.viewmodels.SavedItem


fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic
                    ), start, end
                )
            }

            is UnderlineSpan -> addStyle(
                SpanStyle(textDecoration = TextDecoration.Underline), start, end
            )

            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)), start, end
            )
        }
    }
}

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}

fun String.getHtmlFormattedString() =
    HtmlCompat.fromHtml(this@getHtmlFormattedString, HtmlCompat.FROM_HTML_MODE_COMPACT)
        .toAnnotatedString().toString()


fun FeedItem.toFavCompanyItem(): FavCompanyItem {
    return FavCompanyItem(
        companyName = companyName,
        companyDesc = companyDescription,
        articleTitle = feedTitle,
        companyLogo = companyLogoUrl
    )
}

fun List<FeedItem>.toSavedItemList(): List<SavedItem> {
    return map { feedItem ->
        SavedItem(
            id = feedItem.id,
            companyName = feedItem.companyName,
            articleTitle = feedItem.feedTitle,
            content = feedItem.feedContent ?: "",
            companyLogo = feedItem.companyLogoUrl,
            authorName = feedItem.feedAuthor
        )
    }
}