package net.obesephoenix.koalakraftcrystals.commands

import org.bukkit.command.CommandExecutor
import net.obesephoenix.koalakraftcrystals.commands.KKCommand
import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler
import net.obesephoenix.koalakraftcrystals.util.KKMessage
import net.obesephoenix.koalakraftcrystals.commands.DefaultCommand
import net.obesephoenix.koalakraftcrystals.commands.GiveCrystalCommand
import net.obesephoenix.koalakraftcrystals.commands.CrystalInfoCommand
import net.obesephoenix.koalakraftcrystals.commands.DisableCrystalCommand
import net.obesephoenix.koalakraftcrystals.commands.ReverseNetheriteCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.ArrayList

class KKCommandHandler : CommandExecutor {
    override fun onCommand(
        commandSender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        if (label != "kkc") return false
        if (args.isNotEmpty()) {
            for (c in commands) {
                if (args[0].equals(c.name, ignoreCase = true)) {
                    return if (commandSender.hasPermission(c.permission)) {
                        c.execute(commandSender, *args)
                    } else {
                        commandSender.sendMessage(KKMessage.format("error.no-permission"))
                        true
                    }
                }
            }
        }
        return getCommand("default")!!.execute(commandSender, *args)
    }

    companion object {
        private val commands: MutableList<KKCommand> = ArrayList()

        fun registerCommands() {
            registerCommand(DefaultCommand())
            registerCommand(GiveCrystalCommand())
            registerCommand(CrystalInfoCommand())
            registerCommand(DisableCrystalCommand())
            registerCommand(ReverseNetheriteCommand())
        }

        private fun registerCommand(command: KKCommand) {
            commands.add(command)
        }

        fun getCommands(): List<KKCommand> {
            return commands
        }

        fun getCommandByID(id: String?): KKCommand? {
            for (command in commands) {
                if (command.id.equals(id, ignoreCase = true)) {
                    return command
                }
            }
            return null
        }

        fun getCommand(name: String?): KKCommand? {
            for (command in commands) {
                if (command.name.equals(name, ignoreCase = true)) {
                    return command
                }
            }
            return null
        }
    }
}