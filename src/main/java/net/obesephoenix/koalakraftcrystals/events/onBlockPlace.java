package net.obesephoenix.koalakraftcrystals.events;

import net.obesephoenix.koalakraftcrystals.crystals.Crystal;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onEvent(BlockPlaceEvent e) {
        ItemStack i = e.getItemInHand();
        ItemMeta m = i.getItemMeta();
        if (m == null) return;

        if (m.getPersistentDataContainer().has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
            e.setCancelled(true);
        }
    }

}
