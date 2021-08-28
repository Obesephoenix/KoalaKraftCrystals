package net.obesephoenix.koalakraftcrystals;

import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler;
import net.obesephoenix.koalakraftcrystals.commands.KKTabCompleter;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import net.obesephoenix.koalakraftcrystals.events.KKEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class KoalaKraftCrystals extends JavaPlugin {

    public static KoalaKraftCrystals instance;

    @Override
    public void onEnable() {
        instance = this;

        KKCommandHandler.registerCommands();
        KKCrystalHandler.registerCrystals();
        KKEventHandler.registerEvents(this);

        this.getCommand("kkc").setExecutor(new KKCommandHandler());
        this.getCommand("kkc").setTabCompleter(new KKTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
