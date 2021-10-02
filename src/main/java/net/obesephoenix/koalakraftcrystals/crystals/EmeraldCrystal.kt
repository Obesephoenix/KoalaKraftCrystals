package net.obesephoenix.koalakraftcrystals.crystals

import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.block.Biome
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class EmeraldCrystal : Crystal(
    "emerald",
    "emerald_crystal",
    "http://textures.minecraft.net/texture/8926c1f2c3c14d086c40cfc235fe938694f4a51067ada4726b486ea1c87b03e2",
    "jungle",
    listOf("Haste 2", "Jump Boost 2"),
    ChatColor.GREEN,
    ChatColor.DARK_GREEN
) {
    override fun grantEffects(player: Player?): Boolean {
        val biomes = listOf(
            Biome.JUNGLE, Biome.JUNGLE_EDGE, Biome.JUNGLE_HILLS, Biome.BAMBOO_JUNGLE_HILLS,
            Biome.BAMBOO_JUNGLE, Biome.BAMBOO_JUNGLE_HILLS, Biome.MODIFIED_JUNGLE, Biome.MODIFIED_JUNGLE_EDGE
        )
        if (biomes.contains(player!!.location.block.biome)) {
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.FAST_DIGGING, 340, 1,
                    false, false, true
                )
            )
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.JUMP, 340, 1,
                    false, false, true
                )
            )
            return true
        }
        return false
    }

    override fun addLorePrefix(lore: MutableList<String?>): MutableList<String?> {
        lore.add(" ")
        lore.add(ChatColor.GREEN.toString() + "A gem that contains the spirit of")
        lore.add(ChatColor.GREEN.toString() + "the jungle. You can feel it pulse,")
        lore.add(ChatColor.GREEN.toString() + "like a beating heart.")
        return lore
    }
}