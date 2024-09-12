package features.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = Typography(
    titleSmall = TextStyle(
        fontFamily = gilroyBold, // Custom Font ,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),

    bodyMedium = TextStyle(
        fontFamily = gilroyLight, // Custom Font
        fontSize = 44.sp,
        fontWeight = FontWeight.Light
    ),
    bodyLarge = TextStyle(
        fontFamily = gilroySemibold, // Custom Font
        fontSize = 82.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodySmall = TextStyle(
        fontFamily = gilroySemibold, // Custom Font
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelMedium = TextStyle(
        fontFamily = gilroySemibold, // Custom Font
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelSmall = TextStyle(
        fontFamily = gilroySemibold, // Custom Font
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
)