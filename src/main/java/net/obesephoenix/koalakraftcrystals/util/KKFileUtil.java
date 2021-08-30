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

    public static CrystalConfigFile getConfigFile() {
        return new CrystalConfigFile();
    }

    private static class CrystalConfigFile {

        File file;
        FileConfiguration config;

        private CrystalConfigFile() {
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

        private FileConfiguration saveConfig() {
            try {
                config.save(file);
            } catch(IOException e) {
                e.printStackTrace();
                KoalaKraftCrystals.instance.getLogger().severe("Failed to save crystals config!");
            }
            return config;
        }

        private FileConfiguration reloadConfig() {
            config = YamlConfiguration.loadConfiguration(file);
            return config;
        }

        private Object get(String key) {
            return config.get(key);
        }

    }

}
