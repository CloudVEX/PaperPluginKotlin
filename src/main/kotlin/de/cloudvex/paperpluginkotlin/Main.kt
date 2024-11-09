package de.cloudvex.paperpluginkotlin

import de.cloudvex.paperpluginkotlin.commands.HelloCommand
import gg.flyte.twilight.Twilight
import gg.flyte.twilight.twilight
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.laturia.astral.Astral

@Suppress("UnstableApiUsage")
class Main : Astral() {

    companion object {
        lateinit var INSTANCE: Astral
        lateinit var twilight: Twilight

        lateinit var prefix: Component
    }

    override fun load() {
        logger.info("loading..")
        loadMeta()

        val manager = this.lifecycleManager

        // Docs: https://docs.papermc.io/paper/dev/commands
        manager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val commands = event.registrar()

            // Commands
            HelloCommand.register(commands)
        }
    }

    override fun startup() {
        logger.info("enabling..")
        INSTANCE = this
        twilight = twilight(this)

        config.options().copyDefaults(true)
        saveDefaultConfig()

        // Events
    }

    override fun shutdown() {
        logger.info("shutting down..")
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