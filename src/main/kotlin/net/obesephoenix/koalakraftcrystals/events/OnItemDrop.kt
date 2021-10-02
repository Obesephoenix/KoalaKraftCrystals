package net.obesephoenix.koalakraftcrystals.events

import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler.isCrystal
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.entity.Player
import net.obesephoenix.koalakraftcrystals.util.KKMessage
import org.bukkit.scheduler.BukkitRunnable
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import java.util.HashMap
import java.util.function.Consumer

class OnItemDrop : Listener {
    @EventHandler
    fun onEvent(e: PlayerDropItemEvent) {
        val p = e.player
        val i = e.itemDrop.itemStack
        if (isCrystal(i)) {
            if (!toDrop.containsKey(i)) {
                e.isCancelled = true
                val messages = KKMessage.formatMultiline("no-drop")
                messages.forEach(Consumer { s: String? -> p.sendMessage(s) })
                toDrop[i] = p
                object : BukkitRunnable() {
                    override fun run() {
                        toDrop.remove(i, p)
                    }
                }.runTaskLater(KoalaKraftCrystals.instance, 100L)
            } else {
                toDrop.remove(i, p)
            }
        }
    }

    companion object {
        private val toDrop: MutableMap<ItemStack, Player> = HashMap()
    }
}