package de.cloudvex.paperpluginkotlin.extensions

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

class AdventureExtension {
    companion object {
        fun String.deserialize(): Component {
            return MiniMessage.miniMessage().deserialize(this)
        }
        fun Component.serialize(): String {
            return MiniMessage.miniMessage().serialize(this)
        }
    }
}