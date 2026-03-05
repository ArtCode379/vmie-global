package shop.vmieglobal.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = GreenLight,
    onPrimary = Color(0xFF003907),
    primaryContainer = GreenDark,
    onPrimaryContainer = GreenPale,
    secondary = TealMedium,
    onSecondary = Color.White,
    secondaryContainer = TealDark,
    onSecondaryContainer = Color(0xFFB2DFDB),
    tertiary = AmberAccent,
    onTertiary = Color(0xFF3E2000),
    background = Color(0xFF1A1C1A),
    onBackground = Color(0xFFE2E3DE),
    surface = Color(0xFF1A1C1A),
    onSurface = Color(0xFFE2E3DE),
    surfaceVariant = Color(0xFF404943),
    onSurfaceVariant = Color(0xFFBFC9C2),
    outline = Color(0xFF899489),
)

private val LightColorScheme = lightColorScheme(
    primary = GreenMedium,
    onPrimary = Color.White,
    primaryContainer = GreenPale,
    onPrimaryContainer = GreenDark,
    secondary = TealMedium,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFB2DFDB),
    onSecondaryContainer = TealDark,
    tertiary = AmberAccent,
    onTertiary = Color.White,
    tertiaryContainer = AmberLight,
    onTertiaryContainer = Color(0xFF3E2000),
    background = SurfaceLight,
    onBackground = OnSurfaceDark,
    surface = Color.White,
    onSurface = OnSurfaceDark,
    surfaceVariant = GreenPale,
    onSurfaceVariant = Color(0xFF3D4E40),
    outline = OutlineGreen,
)

@Composable
fun ProductAppSkeletonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
