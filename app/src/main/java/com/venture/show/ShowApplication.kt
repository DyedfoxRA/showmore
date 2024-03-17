package com.venture.show

import android.app.Application
import com.venture.network.networkModule
import com.venture.store_products.di.productsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShowApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@ShowApplication)
            modules(networkModule, productsModule)
        }
    }
}