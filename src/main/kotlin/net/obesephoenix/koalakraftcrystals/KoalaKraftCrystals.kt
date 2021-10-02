package net.obesephoenix.koalakraftcrystals

import org.bukkit.plugin.java.JavaPlugin
import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler
import net.obesephoenix.koalakraftcrystals.commands.KKTabCompleter
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler
import net.obesephoenix.koalakraftcrystals.events.KKEventHandler
import net.obesephoenix.koalakraftcrystals.util.KKFileUtil

class KoalaKraftCrystals : JavaPlugin() {

    override fun onEnable() {
        instance = this

        KKCommandHandler.registerCommands()
        KKCrystalHandler.registerCrystals()
        KKEventHandler.registerEvents(this)

        configFile = KKFileUtil.configFile!!

        getCommand("kkc")!!.setExecutor(KKCommandHandler())
        getCommand("kkc")!!.tabCompleter = KKTabCompleter()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        lateinit var instance: KoalaKraftCrystals
        lateinit var configFile: KKFileUtil.CrystalConfigFile
    }

}