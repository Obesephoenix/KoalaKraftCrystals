package net.obesephoenix.koalakraftcrystals.crystals

import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.block.Biome
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class TopazCrystal : Crystal(
    "topaz",
    "topaz_crystal",
    "http://textures.minecraft.net/texture/1847fd5517c7de90e88c4aa6acbe250ed42e928d46a653b81e0956e459e8295a",
    "desert",
    listOf("Regeneration 2", "Saturation 3"),
    ChatColor.GOLD,
    ChatColor.YELLOW
) {
    override fun grantEffects(player: Player?): Boolean {
        val biomes = listOf(Biome.DESERT, Biome.DESERT_HILLS, Biome.DESERT_LAKES)
        if (biomes.contains(player!!.location.block.biome)) {
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.REGENERATION, 340, 1,
                    false, false, true
                )
            )
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.SATURATION, 340, 2,
                    false, false, true
                )
            )
            return true
        }
        return false
    }

    override fun addLorePrefix(lore: MutableList<String?>): MutableList<String?> {
        lore.add(" ")
        lore.add(ChatColor.GOLD.toString() + "A golden gem that shines as bright")
        lore.add(ChatColor.GOLD.toString() + "as the sun. It's warm glow fills")
        lore.add(ChatColor.GOLD.toString() + "you with strength.")
        return lore
    }
}