package com.venture.store_products.data.repo

import com.venture.core.domain.BaseError
import com.venture.core.domain.DataError
import com.venture.core.domain.ResultResponse
import com.venture.store_products.data.models.Product
import com.venture.store_products.data.service.ProductsServiceApi
import com.venture.store_products.domain.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response

class ProductRepositoryImpl(
    private val productsServiceApi: ProductsServiceApi,
) : ProductRepository {

    override suspend fun getAllProducts(): Flow<ResultResponse<List<Product>, BaseError>> =
        flow {
            emit(ResultResponse.Loading)
            kotlinx.coroutines.delay(2000)
            emit(ResultResponse.Error(DataError.Network.UNKNOWN))
            kotlinx.coroutines.delay(2000)
            emit(ResultResponse.Loading)
            kotlinx.coroutines.delay(2000)
            try {
                val response = productsServiceApi.getAllProducts()
                if (response.isSuccessful && response.body() != null) {
                    emit(ResultResponse.Success(response.body()!!))
                } else {
                    emit(ResultResponse.Error(DataError.Network.UNKNOWN))
                }
            } catch (e: HttpException) {
                when (e.code()) {
                    408 -> ResultResponse.Error(DataError.Network.REQUEST_TIMEOUT)
                    413 -> ResultResponse.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                    else -> ResultResponse.Error(DataError.Network.UNKNOWN)
                }
            } catch (e: Exception) {
                emit(ResultResponse.Error(DataError.Network.UNKNOWN))
            } catch (e: SerializationException) {
                emit(ResultResponse.Error(DataError.Network.SERIALIZATION))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getProductById(id: Int): Flow<Response<Product>> = flow {
        emit(productsServiceApi.getProductById(id))
    }.flowOn(Dispatchers.IO)

    override suspend fun getProductsLimited(limit: Int): Flow<Response<List<Product>>> = flow {
        emit(productsServiceApi.getProductsLimited(limit))
    }.flowOn(Dispatchers.IO)

    override suspend fun getProductsSorted(sort: String): Flow<Response<List<Product>>> = flow {
        emit(productsServiceApi.getProductsSorted(sort))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllCategories(): Flow<Response<List<String>>> = flow {
        emit(productsServiceApi.getAllCategories())
    }.flowOn(Dispatchers.IO)

    override suspend fun getProductsByCategory(
        category: String,
        limit: Int?,
        sort: String?,
    ): Flow<Response<List<Product>>> = flow {
        emit(productsServiceApi.getProductsByCategory(category, limit, sort))
    }.flowOn(Dispatchers.IO)

    override suspend fun addNewProduct(newProduct: Product): Flow<Response<Product>> = flow {
        emit(productsServiceApi.addNewProduct(newProduct))
    }.flowOn(Dispatchers.IO)

    override suspend fun updateProduct(id: Int, productUpdate: Product): Flow<Response<Product>> =
        flow {
            emit(productsServiceApi.updateProduct(id, productUpdate))
        }.flowOn(Dispatchers.IO)

    override suspend fun patchProduct(id: Int, productUpdate: Product): Flow<Response<Product>> =
        flow {
            emit(productsServiceApi.patchProduct(id, productUpdate))
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteProduct(id: Int): Flow<Response<Product>> = flow {
        emit(productsServiceApi.deleteProduct(id))
    }.flowOn(Dispatchers.IO)
}
