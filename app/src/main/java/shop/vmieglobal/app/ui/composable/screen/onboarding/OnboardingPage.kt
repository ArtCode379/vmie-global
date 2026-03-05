package shop.vmieglobal.app.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingPage(onboardingContent: OnboardingContent) {
    Column(modifier = Modifier.fillMaxSize()) {

        // Full-bleed image occupying 55% of screen height
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f)
        ) {
            Image(
                painter = painterResource(id = onboardingContent.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Gradient overlay at bottom of image for smooth transition
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
        }

        // Text content area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 28.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Category accent line
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth(0.15f)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
                    )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(onboardingContent.titleRes),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(onboardingContent.descriptionRes),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
        }
    }
}
