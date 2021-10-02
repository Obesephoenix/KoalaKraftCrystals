package net.obesephoenix.koalakraftcrystals.crystals

import net.obesephoenix.koalakraftcrystals.crystals.GodModeHandler.isGodMode
import net.obesephoenix.koalakraftcrystals.crystals.GodModeHandler.setGodMode
import org.bukkit.NamespacedKey
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import net.obesephoenix.koalakraftcrystals.util.KKFileUtil
import org.bukkit.inventory.ItemStack
import java.util.ArrayList
import java.util.function.Consumer

object KKCrystalHandler {
    private val crystals: MutableList<Crystal> = ArrayList()

    val crystalID_key = NamespacedKey(KoalaKraftCrystals.instance, "crystal_id")

    fun registerCrystals() {
        crystals.clear()
        registerCrystal(TopazCrystal())
        registerCrystal(SapphireCrystal())
        registerCrystal(OnyxCrystal())
        registerCrystal(EmeraldCrystal())
        registerCrystal(RubyCrystal())
        object : BukkitRunnable() {
            override fun run() {
                for (player in Bukkit.getOnlinePlayers()) {
                    player.inventory.forEach(Consumer forEach@ { item: ItemStack? ->
                        if (item == null) return@forEach
                        val meta = item.itemMeta ?: return@forEach
                        if (meta.persistentDataContainer.has(crystalID_key, PersistentDataType.STRING)) {
                            val id = meta.persistentDataContainer.get(crystalID_key, PersistentDataType.STRING)
                            val crystal = getCrystalByID(id)!!
                            if (crystal.grantEffects(player)) {
                                player.addPotionEffect(
                                    PotionEffect(
                                        PotionEffectType.INCREASE_DAMAGE, 340, 1,
                                        false, false, true
                                    )
                                )
                            }
                        }
                    })
                    val playerCrystals = getCrystalsFromPlayer(player)
                    if (playerCrystals.size >= crystals.size) {
                        if (!isGodMode(player)) {
                            setGodMode(player)
                        }
                    } else if (isGodMode(player)) {
                        setGodMode(player, false)
                    }
                }
            }
        }.runTaskTimer(KoalaKraftCrystals.instance, 0L, 120L)
    }

    private fun registerCrystal(crystal: Crystal) {
        crystals.add(crystal)
    }


    fun getCrystals(): List<Crystal> {
        val var1: MutableList<Crystal> = ArrayList()
        crystals.forEach(Consumer forEach@ { crystal: Crystal ->
            if (!KKFileUtil.configFile!!.get().getBoolean(crystal.id + ".enabled")) {
                return@forEach
            }
            var1.add(crystal)
        })
        return var1
    }

    fun getCrystal(name: String?): Crystal? {
        for (crystal in crystals) {
            if (crystal.name.equals(name, ignoreCase = true)) {
                return crystal
            }
        }
        return null
    }

    /**
     *
     * Provides a list of all existing crystals, for most use cases use getCrystal()
     * @return A list of all existing crystals
     */
    val rawCrystals: List<Crystal>
        get() = crystals

    fun getCrystalByID(id: String?): Crystal? {
        for (crystal in crystals) {
            if (crystal.id.equals(id, ignoreCase = true)) {
                return crystal
            }
        }
        return null
    }

    fun getCrystalsFromPlayer(player: Player): List<Crystal?> {
        val playerCrystals: MutableList<Crystal?> = ArrayList()
        player.inventory.forEach(Consumer forEach@ { item: ItemStack? ->
            if (item == null) return@forEach
            val meta = item.itemMeta ?: return@forEach
            if (meta.persistentDataContainer.has(crystalID_key, PersistentDataType.STRING)) {
                val crystal = getCrystalByID(
                    meta.persistentDataContainer.get(
                        crystalID_key,
                        PersistentDataType.STRING
                    )
                )
                playerCrystals.add(crystal)
            }
        })
        return playerCrystals
    }

    fun getPlayerFromCrystal(crystal: Crystal): List<Player> {
        val players: MutableList<Player> = ArrayList()
        for (p in Bukkit.getOnlinePlayers()) {
            for (i in p.inventory) {
                if (i == null) continue
                val meta = i.itemMeta ?: continue
                if (meta.persistentDataContainer.has(crystalID_key, PersistentDataType.STRING)) {
                    if (meta.persistentDataContainer.get(crystalID_key, PersistentDataType.STRING) == crystal.id) {
                        players.add(p)
                    }
                }
            }
        }
        return players
    }

    @JvmStatic
    fun isCrystal(i: ItemStack): Boolean {
        val meta = i.itemMeta ?: return false
        return meta.persistentDataContainer.has(crystalID_key, PersistentDataType.STRING)
    }
}