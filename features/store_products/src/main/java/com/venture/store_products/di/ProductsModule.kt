package com.venture.store_products.di

import com.venture.network.utils.extantions.createService
import com.venture.store_products.data.repo.ProductRepositoryImpl
import com.venture.store_products.data.service.ProductsServiceApi
import com.venture.store_products.domain.ProductRepository
import com.venture.store_products.presentaion.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val productsModule = module {

    single<ProductsServiceApi> { get<Retrofit>().createService() }

    single<ProductRepository> {
        ProductRepositoryImpl(productsServiceApi = get())
    }

    viewModel { ProductsViewModel(productRepository = get()) }
}