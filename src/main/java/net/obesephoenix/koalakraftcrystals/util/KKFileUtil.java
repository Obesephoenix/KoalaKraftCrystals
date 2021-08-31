package net.obesephoenix.koalakraftcrystals.util;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import net.obesephoenix.koalakraftcrystals.crystals.Crystal;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class KKFileUtil {

    private static CrystalConfigFile configFile = null;

    public static CrystalConfigFile getConfigFile() {
        if (configFile == null) {
            configFile = new CrystalConfigFile();
        }
        return configFile;
    }

    public static class CrystalConfigFile {

        File file;
        FileConfiguration config;

        public CrystalConfigFile() {
            file = new File(KoalaKraftCrystals.instance.getDataFolder(), "/crystals.yml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    KoalaKraftCrystals.instance.getLogger().severe("Failed to create config file!");
                }
            }

            config = YamlConfiguration.loadConfiguration(file);

            for (Crystal crystal : KKCrystalHandler.getCrystals()) {
                config.addDefault(crystal.getID() + ".enabled",
                        true);
            }

            config.options().copyDefaults(true);
            this.saveConfig();
        }

        public FileConfiguration saveConfig() {
            try {
                config.save(file);
            } catch(IOException e) {
                e.printStackTrace();
                KoalaKraftCrystals.instance.getLogger().severe("Failed to save crystals config!");
            }
            return config;
        }

        public FileConfiguration reloadConfig() {
            config = YamlConfiguration.loadConfiguration(file);
            return config;
        }

        public FileConfiguration get() {
            return config;
        }

    }

}
