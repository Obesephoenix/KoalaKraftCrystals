package net.obesephoenix.koalakraftcrystals.commands

import net.obesephoenix.koalakraftcrystals.util.KKMessage
import org.bukkit.Bukkit
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler
import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import org.bukkit.Sound
import java.lang.NumberFormatException
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.ArrayList
import java.util.function.Consumer

class GiveCrystalCommand : KKCommand(
    "givecrystal", "givecrystal_command", "/kkc givecrystal <player> <crystal> <amount>",
    "koalakraftcrystals.givecrystal"
) {
    override fun execute(sender: CommandSender, vararg args: String): Boolean {
        if (args.size < 3) {
            sender.sendMessage(KKMessage.format("error.invalid-args", arrayOf(), false))
            return true
        }
        val player = Bukkit.getPlayer(args[1])
        if (player == null) {
            sender.sendMessage(KKMessage.format("error.no-player", arrayOf(args[1]), false))
            return true
        }
        if (args[2].equals("all", ignoreCase = true)) {
            KKCrystalHandler.getCrystals()
                .forEach(Consumer { crystal: Crystal -> player.inventory.addItem(crystal.createItemStack()) })
            player.playSound(player.location, Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f)
            sender.sendMessage(KKMessage.format("givecrystal-command.success.all", arrayOf(player.displayName)))
            return true
        }
        val crystal = KKCrystalHandler.getCrystalByID(args[2])
        if (crystal == null) {
            sender.sendMessage(KKMessage.format("error.no-crystal", arrayOf(args[2]), false))
            return true
        }
        val amount: Int = if (args.size == 4) {
            try {
                args[3].toInt()
            } catch (e: NumberFormatException) {
                sender.sendMessage(KKMessage.format("error.invalid-int", arrayOf(args[3]), false))
                sender.sendMessage(ChatColor.DARK_RED.toString() + usage)
                return true
            }
        } else {
            1
        }
        for (c in 0 until amount) {
            player.inventory.addItem(crystal.createItemStack())
        }
        player.playSound(player.location, Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f)
        sender.sendMessage(
            KKMessage.format(
                "givecrystal-command.success.single", arrayOf(
                    crystal.id,
                    amount, player.displayName
                )
            )
        )
        return true
    }

    override fun tabComplete(sender: CommandSender, vararg args: String): List<String> {
        val options: MutableList<String> = ArrayList()
        when (args.size) {
            2 -> StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.onlinePlayers, options)
            3 -> {
                val ids: MutableList<String> = KKTabCompletionUtil.crystalIDs.toMutableList()
                ids.add("all")
                StringUtil.copyPartialMatches(args[2], ids, options)
            }
        }
        return options
    }
}