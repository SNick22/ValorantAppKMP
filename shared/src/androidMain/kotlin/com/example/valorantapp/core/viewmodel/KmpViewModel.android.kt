package com.example.valorantapp.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class KmpViewModel actual constructor(): ViewModel() {
    protected actual val scope: CoroutineScope
        get() = viewModelScope
}
