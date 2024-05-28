package com.venture.store_products.presentaion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.core.domain.BaseError
import com.venture.core.domain.ResultResponse
import com.venture.store_products.data.models.Product
import com.venture.store_products.domain.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {
    private val _productsResponse =
        MutableStateFlow<ResultResponse<List<Product>, BaseError>>(ResultResponse.Loading)
    val productsResponse: StateFlow<ResultResponse<List<Product>, BaseError>> =
        _productsResponse.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productRepository.getAllProducts().collect { response ->
                _productsResponse.value = response
            }
        }
    }

    fun retryLoadData(){
        fetchProducts()
    }
}
