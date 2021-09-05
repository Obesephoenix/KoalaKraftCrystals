package net.obesephoenix.koalakraftcrystals.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class KKEventHandler {

    public static void registerEvents(JavaPlugin plugin) {
        registerEvent(new onEntityDamage(), plugin);
        registerEvent(new onItemDespawn(), plugin);
        registerEvent(new onBlockPlace(), plugin);
        registerEvent(new onItemDrop(), plugin);
    }

    private static void registerEvent(Listener listener, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

}
