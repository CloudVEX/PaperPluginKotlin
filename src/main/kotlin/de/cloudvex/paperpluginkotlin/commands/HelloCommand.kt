package de.cloudvex.paperpluginkotlin.commands

import com.mojang.brigadier.Command
import io.papermc.paper.command.brigadier.Commands

object HelloCommand {
    fun register(commands: Commands) {
        val command = Commands.literal("hello")
            .executes { ctx ->
                ctx.source.sender.sendMessage("<${ctx.source.sender.name}> Hello")
                ctx.source.sender.sendPlainMessage("<Server> world! :D")
                Command.SINGLE_SUCCESS
            }
            .build()

        val description = "Returns \"world! :D\" to test if the plugin works correctly."
        val aliases = listOf("")

        commands.register(command, description, aliases)
    }
}
