package net.obesephoenix.koalakraftcrystals.commands

import net.obesephoenix.koalakraftcrystals.commands.KKCommand
import net.obesephoenix.koalakraftcrystals.util.KKMessage
import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler
import net.obesephoenix.koalakraftcrystals.util.KKFileUtil
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.ArrayList
import java.util.function.Consumer

class DisableCrystalCommand : KKCommand(
    "disablecrystal", "disablecrystal_command", "/kkc disablecrystal <crystal>",
    "koalakraftcyrstals.disablecrystal"
) {
    override fun execute(sender: CommandSender, vararg args: String): Boolean {
        if (args.size < 2) {
            sender.sendMessage(KKMessage.format("error.invalid-args", arrayOf(), false))
            return true
        }
        val crystal = KKCrystalHandler.getCrystalByID(args[1])
        if (crystal == null) {
            sender.sendMessage(KKMessage.format("error.no-crystal", arrayOf(args[1]), false))
            return true
        }
        KKFileUtil.configFile!!.get()[crystal.id + ".enabled"] = false
        KKFileUtil.configFile!!.saveConfig()
        KKCrystalHandler.registerCrystals()
        val messages = KKMessage.formatMultiline("disablecrystal-command.success", arrayOf(crystal.id))
        messages.forEach(Consumer { s: String? -> sender.sendMessage(s) })
        return true
    }

    override fun tabComplete(sender: CommandSender, vararg args: String): List<String> {
        val options: MutableList<String> = ArrayList()
        if (args.size == 2) {
            StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.crystalIDs, options)
        }
        return options
    }
}