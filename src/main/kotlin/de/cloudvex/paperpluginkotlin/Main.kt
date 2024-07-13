package de.cloudvex.paperpluginkotlin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component

class Main : KSpigot() {

    companion object {
        lateinit var INSTANCE: KSpigot
    }

    override fun load() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this))
        server.consoleSender.sendMessage(Component.text("» PaperPlugin geladen."))

        // Commands
    }

    override fun startup() {
        INSTANCE = this

        CommandAPI.onEnable()
        config.options().copyDefaults(true)
        saveDefaultConfig()

        // Events
    }

    override fun shutdown() {
        CommandAPI.onDisable()
        server.consoleSender.sendMessage(Component.text("» PaperPlugin geladen."))
    }

    override fun reloadConfig() {
        super.reloadConfig()

        saveDefaultConfig()
        config.options().copyDefaults(true)
        saveConfig()
    }
}
val PluginManager by lazy { Main.INSTANCE }