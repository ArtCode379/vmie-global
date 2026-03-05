package shop.vmieglobal.app.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import shop.vmieglobal.app.R
import shop.vmieglobal.app.ui.theme.GreenDark
import shop.vmieglobal.app.ui.theme.GreenMedium
import shop.vmieglobal.app.ui.viewmodel.VMGLOSplashViewModel

@Composable
fun VMGLOSplashScreen(
    modifier: Modifier = Modifier,
    viewModel: VMGLOSplashViewModel = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboardedState by viewModel.onboardedState.collectAsStateWithLifecycle()

    val scale = remember { Animatable(0.6f) }
    val alpha = remember { Animatable(0f) }
    val taglineAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(700, easing = FastOutSlowInEasing)
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(700)
            )
        }
        delay(500)
        taglineAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
    }

    LaunchedEffect(onboardedState) {
        delay(2500)
        if (onboardedState) {
            onNavigateToHomeScreen()
        } else {
            onNavigateToOnboarding()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(GreenDark, GreenMedium)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            // Logo circle with icon
            Box(
                modifier = Modifier
                    .scale(scale.value)
                    .alpha(alpha.value)
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // App name
            Text(
                text = "VMIE Global",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alpha.value)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = "Your Outdoor Adventure Starts Here",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.80f),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(taglineAlpha.value)
            )
        }

        // Bottom label
        Text(
            text = "vmieglobal.uk",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .alpha(taglineAlpha.value)
        )
    }
}
