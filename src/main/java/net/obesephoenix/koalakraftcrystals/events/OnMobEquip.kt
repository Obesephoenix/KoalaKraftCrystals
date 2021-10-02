package net.obesephoenix.koalakraftcrystals.events;

import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class onMobEquip implements Listener {

    @EventHandler
    public void onEvent(EntityPickupItemEvent e) {
        Entity en = e.getEntity();
        if (en instanceof Player) return;

        ItemStack i = e.getItem().getItemStack();
        ItemMeta m = i.getItemMeta();
        if (m == null) return;

        if (m.getPersistentDataContainer().has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
            e.setCancelled(true);
        }
    }
}
