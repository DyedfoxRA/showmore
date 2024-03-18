package com.venture.store_products.presentaion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.store_products.data.models.Product
import com.venture.store_products.domain.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _products = MutableStateFlow<Response<List<Product>>>(Response.success(emptyList()))
    val products: StateFlow<Response<List<Product>>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productRepository.getAllProducts().collect { response ->
                _products.value = response
            }
        }
    }
}