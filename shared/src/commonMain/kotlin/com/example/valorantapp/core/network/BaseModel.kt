package com.example.valorantapp.core.network

import kotlinx.serialization.Serializable

@Serializable
data class BaseModel<T>(
    val status: Int,
    val data: T
)
