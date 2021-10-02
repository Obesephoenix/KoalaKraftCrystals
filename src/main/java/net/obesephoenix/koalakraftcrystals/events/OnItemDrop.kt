package net.obesephoenix.koalakraftcrystals.events;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import net.obesephoenix.koalakraftcrystals.crystals.GodModeHandler;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class onItemDrop implements Listener {

    private static final Map<ItemStack, Player> toDrop = new HashMap<>();

    @EventHandler
    public void onEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        ItemStack i = e.getItemDrop().getItemStack();

        if (KKCrystalHandler.isCrystal(i)) {
            if (!toDrop.containsKey(i)) {
                e.setCancelled(true);
                List<String> messages = KKMessage.formatMultiline("no-drop");
                messages.forEach(p::sendMessage);
                toDrop.put(i, p);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        toDrop.remove(i, p);
                    }
                }.runTaskLater(KoalaKraftCrystals.instance, 100L);
            } else {
                toDrop.remove(i, p);
            }
        }
    }
}
