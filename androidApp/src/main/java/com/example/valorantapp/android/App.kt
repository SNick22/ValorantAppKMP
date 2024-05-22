package com.example.valorantapp.android

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initShared()
    }
}
