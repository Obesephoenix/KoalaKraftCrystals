package net.obesephoenix.koalakraftcrystals.commands

import net.obesephoenix.koalakraftcrystals.util.KKMessage
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack
import org.bukkit.util.StringUtil
import java.util.*
import java.util.function.Consumer

class ReverseNetheriteCommand : KKCommand(
    "reversenetherite", "reversenetherite_command", "/kkc reversenetherite <player>",
    "koalakraftcrystals.reversenetherite"
) {
    override fun execute(sender: CommandSender, vararg args: String): Boolean {
        if (args.size != 2) {
            sender.sendMessage(KKMessage.format("error.invalid-args", arrayOf(0), false))
            return false
        }

        val player = Bukkit.getPlayer(args[1])
        if (player == null) {
            sender.sendMessage(KKMessage.format("error.no-player", arrayOf(args[1]), false))
            return true
        }

        player.inventory.forEach(Consumer forEach@{ item: ItemStack? ->
            if (item == null) return@forEach
            if (item.type.toString().lowercase(Locale.getDefault()).contains("netherite")) {
                val mat = Material.getMaterial(item.type.toString().replace("NETHERITE", "DIAMOND"))
                    ?: return@forEach
                item.type = mat
            }
        })
        player.playSound(player.location, Sound.ENTITY_WITHER_SPAWN, 3.0f, 1.0f)
        val var1 = KKMessage.formatMultiline("reversenetherite-command.success", arrayOf(player.displayName))
        var1.forEach(Consumer { s: String? -> sender.sendMessage(s) })
        return true
    }

    override fun tabComplete(sender: CommandSender, vararg args: String): MutableList<String> {
        val options: MutableList<String> = ArrayList()

        if (args.size == 2) {
            StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.onlinePlayers, options)
        }

        return options
    }

}