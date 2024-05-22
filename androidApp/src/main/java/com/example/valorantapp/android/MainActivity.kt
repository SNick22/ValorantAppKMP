package com.example.valorantapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import com.example.valorantapp.core.design.MyApplicationTheme
import com.example.valorantapp.core.resources.strings.Localization
import com.example.valorantapp.core.resources.strings.LocalizedStringPool
import com.example.valorantapp.features.agents.AgentsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val languageCode = Locale.current.language
        LocalizedStringPool.changeLocal(languageCode.toLocalization())

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgentsScreen()
                }
            }
        }
    }
}

fun String.toLocalization(): Localization {
    return when (this) {
        "ru" -> Localization.Russian
        else -> Localization.English
    }
}
