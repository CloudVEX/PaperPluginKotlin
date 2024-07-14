package de.cloudvex.paperpluginkotlin

import de.cloudvex.paperpluginkotlin.commands.HelloCommand
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component


class Main : KSpigot() {

    companion object {
        lateinit var INSTANCE: KSpigot
    }

    override fun load() {
        server.consoleSender.sendMessage(Component.text("» PaperPlugin geladen."))

        val manager = this.lifecycleManager

        // Docs: https://docs.papermc.io/paper/dev/commands
        manager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val commands = event.registrar()

            // Commands
            HelloCommand.register(commands)
        }
    }

    override fun startup() {
        INSTANCE = this

        config.options().copyDefaults(true)
        saveDefaultConfig()

        // Events
    }

    override fun shutdown() {
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