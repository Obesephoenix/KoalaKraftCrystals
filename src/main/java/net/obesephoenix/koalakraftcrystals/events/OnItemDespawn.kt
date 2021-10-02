package net.obesephoenix.koalakraftcrystals.events

import org.bukkit.event.entity.ItemDespawnEvent
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.NamespacedKey
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType

class OnItemDespawn : Listener {
    @EventHandler
    fun onEvent(event: ItemDespawnEvent) {
        val entity = event.entity
        val item = entity.itemStack
        val meta = item.itemMeta ?: return
        if (meta.persistentDataContainer.has(
                NamespacedKey(KoalaKraftCrystals.instance, "crystal_id"), PersistentDataType.STRING
            )
        ) {
            event.isCancelled = true
        }
    }
}