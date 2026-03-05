package shop.vmieglobal.app.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import shop.vmieglobal.app.R
import shop.vmieglobal.app.ui.viewmodel.VMGLOOnboardingViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int
)

private val onboardingPagesContent = listOf(
    OnboardingContent(
        titleRes = R.string.page_1_title,
        descriptionRes = R.string.page_1_description,
        imageRes = R.drawable.product_sports,
    ),
    OnboardingContent(
        titleRes = R.string.page_2_title,
        descriptionRes = R.string.page_2_description,
        imageRes = R.drawable.product_fishing,
    ),
    OnboardingContent(
        titleRes = R.string.page_3_title,
        descriptionRes = R.string.page_3_description,
        imageRes = R.drawable.product_bike,
    ),
)

@Composable
fun VMGLOOnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: VMGLOOnboardingViewModel = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    VMGLOOnboardingScreenContent(
        modifier = modifier,
        onOnboardingComplete = viewModel::setOnboarded,
    )
}

@Composable
private fun VMGLOOnboardingScreenContent(
    modifier: Modifier = Modifier,
    onOnboardingComplete: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPagesContent.size })
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) { page ->
            OnboardingPage(onboardingContent = onboardingPagesContent[page])
        }

        ControlPanel(
            currentPage = pagerState.currentPage,
            pageCount = pagerState.pageCount,
            isLastPage = pagerState.currentPage == pagerState.pageCount - 1,
            onNextClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onOnboardingComplete()
                }
            },
            onSkipClick = onOnboardingComplete
        )
    }
}

@Composable
private fun ControlPanel(
    currentPage: Int,
    pageCount: Int,
    isLastPage: Boolean,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = onSkipClick) {
            Text(
                text = stringResource(R.string.skip_button_title),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Page indicator dots
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { index ->
                Box(
                    modifier = Modifier
                        .size(if (index == currentPage) 24.dp else 8.dp, 8.dp)
                        .background(
                            color = if (index == currentPage)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        )
                )
                if (index < pageCount - 1) Spacer(modifier = Modifier.width(0.dp))
            }
        }

        Button(onClick = onNextClick) {
            Text(
                text = if (isLastPage)
                    stringResource(R.string.start_button_title)
                else
                    stringResource(R.string.next_button_title),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
