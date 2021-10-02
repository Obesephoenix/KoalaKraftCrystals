package net.obesephoenix.koalakraftcrystals.events

import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.meta.ItemMeta
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType

class OnBlockPlace : Listener {
    @EventHandler
    fun onEvent(e: BlockPlaceEvent) {
        val i = e.itemInHand
        val m = i.itemMeta ?: return
        if (m.persistentDataContainer.has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
            e.isCancelled = true
        }
    }
}