package com.venture.store_products.domain

import com.venture.store_products.data.models.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {

    suspend fun getAllProducts(): Flow<Response<List<Product>>>

    suspend fun getProductById(id: Int): Flow<Response<Product>>

    suspend fun getProductsLimited(limit: Int): Flow<Response<List<Product>>>

    suspend fun getProductsSorted(sort: String): Flow<Response<List<Product>>>

    suspend fun getAllCategories(): Flow<Response<List<String>>>

    suspend fun getProductsByCategory(
        category: String,
        limit: Int?,
        sort: String?,
    ): Flow<Response<List<Product>>>

    suspend fun addNewProduct(newProduct: Product): Flow<Response<Product>>

    suspend fun updateProduct(id: Int, productUpdate: Product): Flow<Response<Product>>

    suspend fun patchProduct(id: Int, productUpdate: Product): Flow<Response<Product>>

    suspend fun deleteProduct(id: Int): Flow<Response<Product>>
}