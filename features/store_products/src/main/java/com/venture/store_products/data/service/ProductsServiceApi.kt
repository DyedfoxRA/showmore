package com.venture.store_products.data.service

import com.venture.store_products.data.models.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsServiceApi {

    @GET("products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<Product>

    @GET("products")
    suspend fun getProductsLimited(@Query("limit") limit: Int): Response<List<Product>>

    @GET("products")
    suspend fun getProductsSorted(@Query("sort") sort: String): Response<List<Product>>

    @GET("products/categories")
    suspend fun getAllCategories(): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int?,
        @Query("sort") sort: String?,
    ): Response<List<Product>>

    @POST("products")
    suspend fun addNewProduct(@Body newProduct: Product): Response<Product>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body productUpdate: Product): Response<Product>

    @PATCH("products/{id}")
    suspend fun patchProduct(@Path("id") id: Int, @Body productUpdate: Product): Response<Product>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Product>
}