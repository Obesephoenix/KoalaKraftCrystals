package net.obesephoenix.koalakraftcrystals.commands

import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler
import net.obesephoenix.koalakraftcrystals.util.KKMessage
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil
import org.apache.commons.lang.WordUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil
import java.util.function.Consumer

class CrystalInfoCommand : KKCommand(
    "crystalinfo", "crystalinfo_command", "/kkc crystalinfo <player>",
    "koalakraftcrystals.crystalinfo"
) {
    override fun execute(sender: CommandSender, vararg args: String): Boolean {
        if (args.size < 2) {
            sender.sendMessage(KKMessage.format("error.invalid-args", arrayOf(), false))
            return false
        }
        val player = Bukkit.getPlayer(args[1])
        val crystal = KKCrystalHandler.getCrystalByID(args[1])
        val usePlayer: Boolean = if (crystal == null) {
            if (player != null) {
                true
            } else {
                sender.sendMessage(KKMessage.format("error.no-crystal", arrayOf(args[1]), false))
                return true
            }
        } else {
            false
        }
        if (usePlayer) {
            val crystals = KKCrystalHandler.getCrystalsFromPlayer(player!!)
            if (crystals.isNotEmpty()) {
                sender.sendMessage(
                    KKMessage.format(
                        "crystalinfo-command.success.player.found", arrayOf(
                            player.displayName
                        )
                    )
                )
                crystals.forEach(Consumer { c: Crystal? ->
                    sender.sendMessage(
                        ChatColor.GREEN.toString() + " - " + WordUtils.capitalize(
                            c?.name
                        )
                    )
                })
            } else {
                sender.sendMessage(
                    KKMessage.format(
                        "crystalinfo-command.success.player.none", arrayOf(
                            player.displayName
                        )
                    )
                )
            }
        } else {
            val targets = KKCrystalHandler.getPlayerFromCrystal(crystal!!)
            if (targets.size == 0) {
                sender.sendMessage(
                    KKMessage.format(
                        "crystalinfo-command.success.crystal.none", arrayOf(
                            crystal.id
                        )
                    )
                )
            } else {
                sender.sendMessage(
                    KKMessage.format(
                        "crystalinfo-command.success.crystal.found", arrayOf(
                            crystal.id
                        )
                    )
                )
                targets.forEach(Consumer { target: Player -> sender.sendMessage(ChatColor.GREEN.toString() + " - " + target.displayName) })
            }
        }
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