package net.obesephoenix.koalakraftcrystals;

import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class KoalaKraftCrystals extends JavaPlugin {

    public static KoalaKraftCrystals instance;

    @Override
    public void onEnable() {
        instance = this;

        KKCommandHandler.registerCommands();
        KKCrystalHandler.registerCrystals();

        this.getCommand("kkc").setExecutor(new KKCommandHandler());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
