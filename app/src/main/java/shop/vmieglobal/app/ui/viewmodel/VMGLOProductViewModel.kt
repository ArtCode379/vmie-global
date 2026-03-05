package shop.vmieglobal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shop.vmieglobal.app.data.model.VMGLOProduct
import shop.vmieglobal.app.data.model.VMGLOProductCategory
import shop.vmieglobal.app.data.repository.ProductRepository
import shop.vmieglobal.app.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VMGLOProductViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {
    private val _allProductsState =
        MutableStateFlow<DataUiState<List<VMGLOProduct>>>(DataUiState.Initial)

    private val _productsState = MutableStateFlow<DataUiState<List<VMGLOProduct>>>(DataUiState.Initial)
    val productsState: StateFlow<DataUiState<List<VMGLOProduct>>>
        get() = _productsState.asStateFlow()

    private val _selectedCategoriesState = MutableStateFlow<Set<VMGLOProductCategory>>(emptySet())
    val selectedCategoriesState: StateFlow<Set<VMGLOProductCategory>>
        get() = _selectedCategoriesState.asStateFlow()

    init {
        observeProducts()
    }

    fun observeProducts() {
        viewModelScope.launch {
            productRepository.observeAll().collect { products ->
                _allProductsState.value = if (products.isNotEmpty()) {
                    DataUiState.Populated(products)
                } else {
                    DataUiState.Empty
                }

                updateProductsState()
            }
        }
    }

    private fun updateProductsState() {
        val allProductsState = _allProductsState.value
        val selectedCategories = _selectedCategoriesState.value

        _productsState.value = when (allProductsState) {
            DataUiState.Initial -> DataUiState.Initial

            DataUiState.Empty -> DataUiState.Empty

            is DataUiState.Populated -> {
                val filtered = if (selectedCategories.isEmpty()) {
                    allProductsState.data
                } else {
                    allProductsState.data.filter { it.category in selectedCategories }
                }

                if (filtered.isEmpty()) {
                    DataUiState.Empty
                } else {
                    DataUiState.Populated(filtered)
                }
            }

        }
    }


    fun selectCategory(category: VMGLOProductCategory) {
        _selectedCategoriesState.update { selected ->
            if (category in selected) selected - category else selected + category
        }
        updateProductsState()
    }
}