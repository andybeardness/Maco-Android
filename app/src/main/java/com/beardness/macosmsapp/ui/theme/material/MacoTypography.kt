package com.beardness.macosmsapp.ui.theme.material

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.R

private val familyBlack = FontFamily(Font(resId = R.font.commissioner_black))
private val familyExtraBold = FontFamily(Font(resId = R.font.commissioner_extra_bold))
private val familyBold = FontFamily(Font(resId = R.font.commissioner_bold))
private val familySemiBold = FontFamily(Font(resId = R.font.commissioner_semi_bold))
private val familyMedium = FontFamily(Font(resId = R.font.commissioner_medium))
private val familyRegular = FontFamily(Font(resId = R.font.commissioner_regular))
private val familyLight = FontFamily(Font(resId = R.font.commissioner_light))
private val familyExtraLight = FontFamily(Font(resId = R.font.commissioner_extra_light))
private val familyThin = FontFamily(Font(resId = R.font.commissioner_thin))

val MacoTypography: Typography = Typography(
    displayLarge = TextStyle(fontSize = 24.sp, fontFamily = familyBold),
    displayMedium = TextStyle(fontSize = 22.sp, fontFamily = familyBold),
    displaySmall = TextStyle(fontSize = 20.sp, fontFamily = familyBold),

    titleLarge = TextStyle(fontSize = 22.sp, fontFamily = familySemiBold),
    titleMedium = TextStyle(fontSize = 20.sp, fontFamily = familySemiBold),
    titleSmall = TextStyle(fontSize = 18.sp, fontFamily = familySemiBold),

    headlineLarge = TextStyle(fontSize = 20.sp, fontFamily = familyMedium),
    headlineMedium = TextStyle(fontSize = 18.sp, fontFamily = familyMedium),
    headlineSmall = TextStyle(fontSize = 16.sp, fontFamily = familyMedium),

    bodyLarge = TextStyle(fontSize = 18.sp, fontFamily = familyRegular),
    bodyMedium = TextStyle(fontSize = 16.sp, fontFamily = familyRegular),
    bodySmall = TextStyle(fontSize = 14.sp, fontFamily = familyRegular),

    labelLarge = TextStyle(fontSize = 16.sp, fontFamily = familyLight),
    labelMedium = TextStyle(fontSize = 14.sp, fontFamily = familyLight),
    labelSmall = TextStyle(fontSize = 12.sp, fontFamily = familyLight),
)