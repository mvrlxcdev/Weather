package features.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Typeface

private fun loadCustomFont(name: String): Typeface {
    return Typeface.makeFromName(name, FontStyle.NORMAL)
}

actual val gilroyBlack: FontFamily = FontFamily(
    Typeface(loadCustomFont("gilroyblack"))
)
actual val gilroyBold: FontFamily = FontFamily(
    Typeface(loadCustomFont("gilroybold"))
)
actual val gilroyLight: FontFamily= FontFamily(
    Typeface(loadCustomFont("gilroylight"))
)
actual val gilroyMedium: FontFamily= FontFamily(
    Typeface(loadCustomFont("gilroymedium"))
)
actual val gilroySemibold: FontFamily= FontFamily(
    Typeface(loadCustomFont("gilroysemibold"))
)