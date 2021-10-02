package net.obesephoenix.koalakraftcrystals.util

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler.rawCrystals
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

object KKFileUtil {
    @JvmStatic
    var configFile: CrystalConfigFile? = null
        get() {
            if (field == null) {
                field = CrystalConfigFile()
            }
            return field
        }
        private set

    class CrystalConfigFile {
        var file: File = File(KoalaKraftCrystals.instance.dataFolder, "/crystals.yml")
        var config: FileConfiguration
        fun saveConfig(): FileConfiguration {
            try {
                config.save(file)
            } catch (e: IOException) {
                e.printStackTrace()
                KoalaKraftCrystals.instance.logger.severe("Failed to save crystals config!")
            }
            return config
        }

        fun reloadConfig(): FileConfiguration {
            config = YamlConfiguration.loadConfiguration(file)
            return config
        }

        fun get(): FileConfiguration {
            return config
        }

        init {
            if (!file.exists()) {
                file.parentFile.mkdirs()
                try {
                    file.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                    KoalaKraftCrystals.instance.logger.severe("Failed to create config file!")
                }
            }
            config = YamlConfiguration.loadConfiguration(file)
            for (crystal in rawCrystals) {
                config.addDefault(
                    crystal.id + ".enabled",
                    true
                )
            }
            config.options().copyDefaults(true)
            saveConfig()
        }
    }
}