package net.obesephoenix.koalakraftcrystals;

import net.obesephoenix.koalakraftcrystals.commands.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class KoalaKraftCrystals extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CommandHandler.registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
