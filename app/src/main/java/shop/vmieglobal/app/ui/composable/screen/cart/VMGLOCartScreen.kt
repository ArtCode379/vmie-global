package shop.vmieglobal.app.ui.composable.screen.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shop.vmieglobal.app.R
import shop.vmieglobal.app.ui.composable.shared.BottomButtonBox
import shop.vmieglobal.app.ui.composable.shared.DataBasedContainer
import shop.vmieglobal.app.ui.composable.shared.DataEmptyContent
import shop.vmieglobal.app.ui.composable.shared.ItemsList
import shop.vmieglobal.app.ui.state.CartItemUiState
import shop.vmieglobal.app.ui.state.DataUiState
import shop.vmieglobal.app.ui.viewmodel.VMGLOCartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VMGLOCartScreen(
    modifier: Modifier = Modifier,
    viewModel: VMGLOCartViewModel = koinViewModel(),
    onNavigateToVMGLOCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    VMGLOCartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onCompleteOrderButtonClick = onNavigateToVMGLOCheckoutScreen,
    )
}

@Composable
private fun VMGLOCartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(modifier = modifier) {

        DataBasedContainer(
            dataState = cartItemsState,

            dataPopulated = {
                CartPopulated(
                    cartItems = (cartItemsState as DataUiState.Populated).data,
                    totalPrice = totalPrice,
                    onPlusItemClick = onPlusItemClick,
                    onMinusItemClick = onMinusItemClick,
                    onCompleteOrderButtonClick = onCompleteOrderButtonClick,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun CartPopulated(
    cartItems: List<CartItemUiState>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ItemsList(
            items = cartItems,
            modifier = modifier,
            itemCard = { cartItem ->
                CartItem(
                    cartItem = cartItem,
                    onPlusClick = { onPlusItemClick(cartItem.productId) },
                    onMinusClick = { onMinusItemClick(cartItem.productId) },
                )
            }
        )

        BottomButtonBox(
            onButtonClick = onCompleteOrderButtonClick,
            buttonText = stringResource(R.string.button_place_order_label, totalPrice),
        )
    }
}