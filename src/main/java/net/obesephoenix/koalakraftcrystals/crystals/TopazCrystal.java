package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class TopazCrystal extends  Crystal {

    public TopazCrystal() {
        super("topaz",
                "topaz_crystal",
                "http://textures.minecraft.net/texture/1847fd5517c7de90e88c4aa6acbe250ed42e928d46a653b81e0956e459e8295a",
                "desert",
                Arrays.asList("Regeneration 2", "Saturation 3"),
                ChatColor.GOLD,
                ChatColor.YELLOW);
    }

    @Override
    public void grantEffects(Player player) {
        List<Biome> biomes = Arrays.asList(Biome.DESERT, Biome.DESERT_HILLS, Biome.DESERT_LAKES);
        if (biomes.contains(player.getLocation().getBlock().getBiome())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 340, 1,
                    false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 340, 2,
                    false, false, true));
        }
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add(" ");
        lore.add(ChatColor.GOLD + "A golden gem that shines as bright");
        lore.add(ChatColor.GOLD + "as the sun. It's warm glow fills");
        lore.add(ChatColor.GOLD + "you with strength.");
        return lore;
    }

}
