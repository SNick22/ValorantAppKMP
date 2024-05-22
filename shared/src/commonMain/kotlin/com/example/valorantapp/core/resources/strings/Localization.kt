package com.example.valorantapp.core.resources.strings

import com.example.valorantapp.core.resources.strings.locales.EnglishStrings
import com.example.valorantapp.core.resources.strings.locales.RussianStrings

enum class Localization(internal val stringPool: StringPool) {
    English(EnglishStrings()),
    Russian(RussianStrings())
}
