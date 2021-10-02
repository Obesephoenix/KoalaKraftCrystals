package net.obesephoenix.koalakraftcrystals.crystals

import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.block.Biome
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class RubyCrystal : Crystal(
    "ruby",
    "ruby_crystal",
    "http://textures.minecraft.net/texture/c20ef06dd60499766ac8ce15d2bea41d2813fe55718864b52dc41cbaae1ea913",
    "nether",
    listOf("Fire Resistance", "Resistance 2"),
    ChatColor.RED,
    ChatColor.DARK_RED
) {
    override fun grantEffects(player: Player?): Boolean {
        val biomes = listOf(
            Biome.NETHER_WASTES, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST, Biome.SOUL_SAND_VALLEY,
            Biome.BASALT_DELTAS
        )
        if (biomes.contains(player!!.location.block.biome)) {
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.FIRE_RESISTANCE, 340, 0,
                    false, false, true
                )
            )
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.DAMAGE_RESISTANCE, 340, 1,
                    false, false, true
                )
            )
            return true
        }
        return false
    }

    override fun addLorePrefix(lore: MutableList<String?>): MutableList<String?> {
        lore.add(" ")
        lore.add(ChatColor.RED.toString() + "A gem forged from the fiery")
        lore.add(ChatColor.RED.toString() + "depths of hell. You can still feel")
        lore.add(ChatColor.RED.toString() + "the heat emanating from its core.")
        return lore
    }
}