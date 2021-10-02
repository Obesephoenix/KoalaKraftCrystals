package net.obesephoenix.koalakraftcrystals.events;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import javax.naming.Name;

public class onEntityDamage implements Listener {

    @EventHandler
    public void onEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Item)) return;
        Item entity = (Item) event.getEntity();
        ItemStack item = entity.getItemStack();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(new NamespacedKey(KoalaKraftCrystals.instance, "crystal_id")
                , PersistentDataType.STRING)) {
            event.setCancelled(true);
        }
    }
}
