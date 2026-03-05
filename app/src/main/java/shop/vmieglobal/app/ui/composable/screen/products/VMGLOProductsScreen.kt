package shop.vmieglobal.app.ui.composable.screen.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shop.vmieglobal.app.R
import shop.vmieglobal.app.data.model.VMGLOProduct
import shop.vmieglobal.app.data.model.VMGLOProductCategory
import shop.vmieglobal.app.ui.composable.shared.DataBasedContainer
import shop.vmieglobal.app.ui.composable.shared.DataEmptyContent
import shop.vmieglobal.app.ui.state.DataUiState
import shop.vmieglobal.app.ui.viewmodel.VMGLOProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VMGLOProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: VMGLOProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()
    val selectedCategoriesState by viewModel.selectedCategoriesState.collectAsState()

    ProductsContent(
        productsState = productsState,
        selectedCategories = selectedCategoriesState,
        modifier = modifier,
        onCategoryClick = viewModel::selectCategory,
        onNavigateToProductDetails = onNavigateToProductDetails,
    )
}

@Composable
private fun ProductsContent(
    productsState: DataUiState<List<VMGLOProduct>>,
    selectedCategories: Set<VMGLOProductCategory>,
    modifier: Modifier = Modifier,
    onCategoryClick: (VMGLOProductCategory) -> Unit,
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {

        VMGLOArticlesCarousel(
            modifier = Modifier.fillMaxWidth(),
        )

        DataBasedContainer<List<VMGLOProduct>>(
            dataState = productsState,

            dataPopulated = {
                // Place the list of products, filters, and other data-dependent content here.
                // If you want to place data-independent elements (e.g. a screen title),
                // put them outside the DataBasedContainer.
                ProductsPopulated(
                    products = (productsState as DataUiState.Populated).data,
                    selectedCategories = selectedCategories,
                    onCategoryClick = onCategoryClick,
                    onNavigateToProductDetails = onNavigateToProductDetails,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ProductsPopulated(
    products: List<VMGLOProduct>,
    selectedCategories: Set<VMGLOProductCategory>,
    modifier: Modifier = Modifier,
    onCategoryClick: (VMGLOProductCategory) -> Unit,
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    Column(modifier) {
        ProductFilter(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface),
            selectedCategories = selectedCategories,
            onCategoryClick = onCategoryClick,
        )

        ProductList(
            products = products,
            onNavigateToProductDetails = onNavigateToProductDetails,
        )
    }
}