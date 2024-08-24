package de.cloudvex.paperpluginkotlin

import de.cloudvex.paperpluginkotlin.commands.HelloCommand
import de.cloudvex.paperpluginkotlin.extensions.AdventureExtension.Companion.deserialize
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component

@Suppress("UnstableApiUsage")
class Main : KSpigot() {

    companion object {
        lateinit var INSTANCE: KSpigot

        lateinit var prefix: Component
    }

    override fun load() {
        loadMeta()
        server.consoleSender.sendMessage(Component.text("» PaperPlugin is loading.."))

        val manager = this.lifecycleManager

        // Docs: https://docs.papermc.io/paper/dev/commands
        manager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val commands = event.registrar()

            // Commands
            HelloCommand.register(commands)
        }
    }

    override fun startup() {
        server.consoleSender.sendMessage(Component.text("» PaperPlugin is enabling.."))
        INSTANCE = this

        config.options().copyDefaults(true)
        saveDefaultConfig()

        // Events
    }

    override fun shutdown() {
        server.consoleSender.sendMessage(Component.text("» PaperPlugin is shutting down."))
    }

    private fun loadMeta() {
        prefix = config.getString("meta.prefix")?.deserialize() ?: kotlin.run {
            server.logger.warning("Meta couldn't be loaded.")
            return
        }
    }

    override fun reloadConfig() {
        super.reloadConfig()

        saveDefaultConfig()
        config.options().copyDefaults(true)
        saveConfig()
    }
}
val PluginManager by lazy { Main.INSTANCE }