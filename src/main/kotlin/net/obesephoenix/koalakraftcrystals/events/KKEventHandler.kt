package net.obesephoenix.koalakraftcrystals.events

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.event.Listener

object KKEventHandler {
    @JvmStatic
    fun registerEvents(plugin: JavaPlugin) {
        registerEvent(OnEntityDamage(), plugin)
        registerEvent(OnItemDespawn(), plugin)
        registerEvent(OnBlockPlace(), plugin)
        registerEvent(OnItemDrop(), plugin)
        registerEvent(OnMobEquip(), plugin)
    }

    private fun registerEvent(listener: Listener, plugin: JavaPlugin) {
        plugin.server.pluginManager.registerEvents(listener, plugin)
    }
}