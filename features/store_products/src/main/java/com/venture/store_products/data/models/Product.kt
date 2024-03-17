package com.venture.store_products.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Product(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("title")
    val title: String,

    @SerialName("price")
    val price: Double,

    @SerialName("category")
    val category: String,

    @SerialName("description")
    val description: String,

    @SerialName("image")
    val image: String,
)
