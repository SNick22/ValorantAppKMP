package com.example.valorantapp.core.resources.strings


object LocalizedStringPool: StringPool {

    private var localization: Localization = Localization.English

    fun changeLocal(local: Localization) {
        localization = local
    }

    fun currentLocal(): Localization = localization

    override val agentsTopBarTitle: String
        get() = localization.stringPool.agentsTopBarTitle
}
