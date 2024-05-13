package com.example.valorantapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform