package shop.vmieglobal.app.ui.composable.screen.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shop.vmieglobal.app.R
import shop.vmieglobal.app.data.entity.OrderEntity
import shop.vmieglobal.app.ui.composable.shared.DataBasedContainer
import shop.vmieglobal.app.ui.composable.shared.DataEmptyContent
import shop.vmieglobal.app.ui.composable.shared.ItemsList
import shop.vmieglobal.app.ui.state.DataUiState
import shop.vmieglobal.app.ui.viewmodel.VMGLOOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VMGLOOrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: VMGLOOrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        DataBasedContainer(
            dataState = ordersState,

            dataPopulated = {
                OrdersPopulated(
                    orders = (ordersState as DataUiState.Populated).data,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun OrdersPopulated(
    orders: List<OrderEntity>,
    modifier: Modifier = Modifier,
) {

    ItemsList(
        items = orders,
        modifier = modifier,
        itemCard = { order -> OrderItem(order) }
    )
}