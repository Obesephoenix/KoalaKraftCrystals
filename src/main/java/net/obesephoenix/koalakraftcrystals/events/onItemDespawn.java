package net.obesephoenix.koalakraftcrystals.events;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class onItemDespawn implements Listener {

    @EventHandler
    public void onEvent(ItemDespawnEvent event) {
        Item entity = event.getEntity();
        ItemStack item = entity.getItemStack();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(new NamespacedKey(KoalaKraftCrystals.instance, "crystal_id")
                , PersistentDataType.STRING)) {
            event.setCancelled(true);
        }
    }
}
