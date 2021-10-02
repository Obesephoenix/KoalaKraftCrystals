package net.obesephoenix.koalakraftcrystals.events

import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType

class OnMobEquip : Listener {
    @EventHandler
    fun onEvent(e: EntityPickupItemEvent) {
        val en: Entity = e.entity
        if (en is Player) return
        val i = e.item.itemStack
        val m = i.itemMeta ?: return
        if (m.persistentDataContainer.has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
            e.isCancelled = true
        }
    }
}