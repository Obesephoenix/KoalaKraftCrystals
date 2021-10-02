package net.obesephoenix.koalakraftcrystals.commands

import org.bukkit.command.TabCompleter
import net.obesephoenix.koalakraftcrystals.commands.KKCommand
import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.ArrayList

class KKTabCompleter : TabCompleter {

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        var options: MutableList<String> = ArrayList()
        var found = false
        if (label.equals("kkc", ignoreCase = true)) {
            if (args.isNotEmpty()) {
                for (cmd in KKCommandHandler.getCommands()) {
                    if (args[0].equals(cmd.name, ignoreCase = true)) {
                        options = cmd.tabComplete(commandSender, *args) as MutableList<String>
                        found = true
                    }
                }
                if (!found) StringUtil.copyPartialMatches(args[0], KKTabCompletionUtil.commands, options)
            }
        }
        return options
    }
}