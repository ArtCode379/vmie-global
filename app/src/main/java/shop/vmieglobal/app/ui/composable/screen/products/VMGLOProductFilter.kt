package shop.vmieglobal.app.ui.composable.screen.products

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import shop.vmieglobal.app.data.model.VMGLOProductCategory

@Composable
fun ProductFilter(
    selectedCategories: Set<VMGLOProductCategory>,
    modifier: Modifier = Modifier,
    onCategoryClick: (VMGLOProductCategory) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(VMGLOProductCategory.entries.toTypedArray()) { category ->
            VMGLOProductCategoryChip(
                isSelected = selectedCategories.contains(category),
                category = category,
                onCategoryClick = onCategoryClick,
                filterChipParameters = FilterChipParameters(
                    selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    borderColor = MaterialTheme.colorScheme.outline,
                    borderWidth = 1.dp
                )
            )
        }
    }
}

@Composable
private fun VMGLOProductCategoryChip(
    isSelected: Boolean,
    category: VMGLOProductCategory,
    modifier: Modifier = Modifier,
    onCategoryClick: (VMGLOProductCategory) -> Unit,
    filterChipParameters: FilterChipParameters,
) {
    val shape = RoundedCornerShape(16.dp)
    val border = BorderStroke(
        width = filterChipParameters.borderWidth,
        color = filterChipParameters.borderColor,
    )

    Surface(
        modifier = modifier
            .clip(shape = shape)
            .clickable(
                onClick = { onCategoryClick(category) }
            ),
        shape = shape,
        color = if (isSelected)
            filterChipParameters.selectedContainerColor
        else
            filterChipParameters.unselectedContainerColor,
        contentColor = if (isSelected)
            filterChipParameters.selectedTextColor
        else
            filterChipParameters.unselectedTextColor,
        border = border,
    ) {
        Text(
            text = stringResource(category.titleRes),
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.5.dp),
            style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Stable
private data class FilterChipParameters(
    val selectedContainerColor: Color = Color.Unspecified,
    val unselectedContainerColor: Color = Color.Unspecified,
    val selectedTextColor: Color = Color.Unspecified,
    val unselectedTextColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val borderWidth: Dp = 0.dp,
)