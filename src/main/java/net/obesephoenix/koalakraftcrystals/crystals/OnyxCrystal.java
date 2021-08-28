package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnyxCrystal extends Crystal {

    public OnyxCrystal() {
        super("onyx",
                "onyx_crystal",
                "http://textures.minecraft.net/texture/f5db564300f9cd66785200799bef89894dc3c36b972722b782cda7d9c928b191",
                "end",
                Arrays.asList("Slow Falling", "Invisibility"),
                ChatColor.DARK_PURPLE,
                ChatColor.LIGHT_PURPLE);
    }

    @Override
    public boolean grantEffects(Player player) {
        List<Biome> biomes = Arrays.asList(Biome.END_BARRENS, Biome.THE_END, Biome.THE_VOID, Biome.END_HIGHLANDS,
                Biome.END_MIDLANDS, Biome.SMALL_END_ISLANDS);
        if (biomes.contains(player.getLocation().getBlock().getBiome())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 340, 0,
                    false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 340, 0,
                    false, false, true));
            return true;
        }
        return false;
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add("");
        lore.add(ChatColor.DARK_PURPLE + "A pure black gem. Staring into");
        lore.add(ChatColor.DARK_PURPLE + "its depths you can't help but feel");
        lore.add(ChatColor.DARK_PURPLE + "an overwhelming sense of dread...");
        return lore;
    }

}
