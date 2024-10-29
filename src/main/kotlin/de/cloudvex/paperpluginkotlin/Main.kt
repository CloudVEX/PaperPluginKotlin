package de.cloudvex.paperpluginkotlin

import de.cloudvex.paperpluginkotlin.commands.HelloCommand
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.laturia.astral.Astral

@Suppress("UnstableApiUsage")
class Main : Astral() {

    companion object {
        lateinit var INSTANCE: Astral

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

    private fun loadMeta(): Boolean? {
        prefix = MiniMessage.miniMessage().deserialize(config.getString("meta.prefix") ?: return null)
        meta.set("prefix", prefix)
        return true
    }


    override fun reloadConfig() {
        super.reloadConfig()

        saveDefaultConfig()
        config.options().copyDefaults(true)
        saveConfig()
    }
}
val PluginManager by lazy { Main.INSTANCE }