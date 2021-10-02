package net.obesephoenix.koalakraftcrystals.crystals

import net.obesephoenix.koalakraftcrystals.crystals.Crystal
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.block.Biome
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class OnyxCrystal : Crystal(
    "onyx",
    "onyx_crystal",
    "http://textures.minecraft.net/texture/f5db564300f9cd66785200799bef89894dc3c36b972722b782cda7d9c928b191",
    "end",
    listOf("Slow Falling", "Invisibility"),
    ChatColor.DARK_PURPLE,
    ChatColor.LIGHT_PURPLE
) {
    override fun grantEffects(player: Player?): Boolean {
        val biomes = listOf(
            Biome.END_BARRENS, Biome.THE_END, Biome.THE_VOID, Biome.END_HIGHLANDS,
            Biome.END_MIDLANDS, Biome.SMALL_END_ISLANDS
        )
        if (biomes.contains(player!!.location.block.biome)) {
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.SLOW_FALLING, 340, 0,
                    false, false, true
                )
            )
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.INVISIBILITY, 340, 0,
                    false, false, true
                )
            )
            return true
        }
        return false
    }

    override fun addLorePrefix(lore: MutableList<String?>): MutableList<String?> {
        lore.add("")
        lore.add(ChatColor.DARK_PURPLE.toString() + "A pure black gem. Staring into")
        lore.add(ChatColor.DARK_PURPLE.toString() + "its depths you can't help but feel")
        lore.add(ChatColor.DARK_PURPLE.toString() + "an overwhelming sense of dread...")
        return lore
    }
}