package net.obesephoenix.koalakraftcrystals.crystals

import org.bukkit.entity.Player
import net.obesephoenix.koalakraftcrystals.crystals.GodModeHandler
import org.bukkit.attribute.Attribute
import java.util.ArrayList

object GodModeHandler {
    private val godPlayers: MutableList<Player> = ArrayList()
    fun setGodMode(player: Player) {
        setGodMode(player, true)
    }

    fun setGodMode(player: Player, `val`: Boolean) {
        if (`val`) {
            godPlayers.add(player)
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 40.0
        } else {
            godPlayers.remove(player)
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 20.0
        }
    }

    fun isGodMode(player: Player): Boolean {
        return godPlayers.contains(player)
    }
}