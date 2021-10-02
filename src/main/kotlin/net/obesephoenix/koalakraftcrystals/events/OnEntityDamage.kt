package net.obesephoenix.koalakraftcrystals.events

import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.NamespacedKey
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType

class OnEntityDamage : Listener {
    @EventHandler
    fun onEvent(event: EntityDamageEvent) {
        if (event.entity !is Item) return
        val entity = event.entity as Item
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