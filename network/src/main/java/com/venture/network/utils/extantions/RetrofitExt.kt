package com.venture.network.utils.extantions

import retrofit2.Retrofit

inline fun <reified T> Retrofit.createService(): T = create(T::class.java)