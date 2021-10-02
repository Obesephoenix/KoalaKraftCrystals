package net.obesephoenix.koalakraftcrystals.util

import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler.getCrystals
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler
import java.util.ArrayList
import java.util.function.Consumer

object KKTabCompletionUtil {
    val onlinePlayers: List<String>
        get() {
            val players: MutableList<String> = ArrayList()
            Bukkit.getOnlinePlayers().forEach { p: Player? -> players.add(p!!.name) }
            return players
        }
    val crystals: List<String>
        get() {
            val crystals: MutableList<String> = ArrayList()
            getCrystals().forEach(Consumer { p: Crystal -> crystals.add(p.name) })
            return crystals
        }
    val crystalIDs: List<String>
        get() {
            val ids: MutableList<String> = ArrayList()
            getCrystals().forEach(Consumer { p: Crystal -> ids.add(p.id) })
            return ids
        }
    val commands: List<String>
        get() {
            val commands: MutableList<String> = ArrayList()
            KKCommandHandler.getCommands().forEach { p ->
                if (!p.name.equals("default", ignoreCase = true)) {
                    commands.add(p.name)
                }
            }
            return commands
        }
}