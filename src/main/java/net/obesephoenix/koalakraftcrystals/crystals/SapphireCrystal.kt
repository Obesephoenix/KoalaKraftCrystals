package net.obesephoenix.koalakraftcrystals.crystals

import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.block.Biome
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class SapphireCrystal : Crystal(
    "sapphire",
    "sapphire_crystal",
    "http://textures.minecraft.net/texture/5ba338334ec4c04a304a083a0a05695d5038eef6a5fadf0fdd6b6e1d9c340235",
    "ocean",
    listOf("Dolphins Grace", "Water Breathing"),
    ChatColor.AQUA,
    ChatColor.BLUE
) {
    override fun grantEffects(player: Player?): Boolean {
        val biomes = listOf(
            Biome.OCEAN, Biome.COLD_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.DEEP_FROZEN_OCEAN,
            Biome.DEEP_LUKEWARM_OCEAN, Biome.DEEP_OCEAN, Biome.WARM_OCEAN, Biome.LUKEWARM_OCEAN, Biome.DEEP_WARM_OCEAN,
            Biome.WARM_OCEAN, Biome.FROZEN_OCEAN
        )
        if (biomes.contains(player!!.location.block.biome)) {
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.WATER_BREATHING, 340, 0,
                    false, false, true
                )
            )
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.DOLPHINS_GRACE, 340, 0,
                    false, false, true
                )
            )
            return true
        }
        return false
    }

    override fun addLorePrefix(lore: MutableList<String?>): MutableList<String?> {
        lore.add(" ")
        lore.add(ChatColor.AQUA.toString() + "A gem as blue as the sea itself. If")
        lore.add(ChatColor.AQUA.toString() + "you listen closely, you can hear the")
        lore.add(ChatColor.AQUA.toString() + "waves on the beach against the shore.")
        return lore
    }
}