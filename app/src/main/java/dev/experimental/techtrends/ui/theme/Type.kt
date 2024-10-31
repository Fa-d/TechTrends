package dev.experimental.techtrends.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import dev.experimental.techtrends.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Roboto"),
        fontProvider = provider,
    )
)

val displayFontFamily = bodyFontFamily/*FontFamily(
    Font(
        googleFont = GoogleFont("Alice"),
        fontProvider = provider,
    )
)
*/
val HomeTypography = Typography(
    titleMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.25.sp,
        fontFamily = displayFontFamily,
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.25.sp,
        fontFamily = displayFontFamily,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W100,
        fontSize = 12.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.25.sp,
        fontFamily = displayFontFamily,
    ),
)
val tabTypography = Typography(
    titleMedium = TextStyle(
        fontWeight = FontWeight.W400, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.25.sp
    )
)

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        fontFamily = displayFontFamily,
    )
)
val FeedContentTypography = Typography(
    titleMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.25.sp,
        fontFamily = displayFontFamily,
    ), titleSmall = TextStyle(
        fontWeight = FontWeight.W300,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.25.sp,
        fontFamily = displayFontFamily,
    )
)

