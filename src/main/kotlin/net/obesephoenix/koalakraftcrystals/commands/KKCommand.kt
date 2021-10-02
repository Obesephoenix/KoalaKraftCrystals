package net.obesephoenix.koalakraftcrystals.commands

import org.bukkit.command.CommandSender

abstract class KKCommand(val name: String, val id: String, val usage: String, val permission: String) {
    abstract fun execute(sender: CommandSender, vararg args: String): Boolean
    abstract fun tabComplete(sender: CommandSender, vararg args: String): List<String>
}