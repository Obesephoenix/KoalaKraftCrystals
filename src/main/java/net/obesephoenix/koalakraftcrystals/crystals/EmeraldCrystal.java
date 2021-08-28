package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmeraldCrystal extends Crystal {

    public EmeraldCrystal() {
        super("emerald",
                "emerald_crystal",
                "http://textures.minecraft.net/texture/8926c1f2c3c14d086c40cfc235fe938694f4a51067ada4726b486ea1c87b03e2",
                "jungle",
                Arrays.asList("Haste 2", "Jump Boost 2"),
                ChatColor.GREEN,
                ChatColor.DARK_GREEN
                );
    }

    @Override
    public boolean grantEffects(Player player) {
        List<Biome> biomes = Arrays.asList(Biome.JUNGLE, Biome.JUNGLE_EDGE, Biome.JUNGLE_HILLS, Biome.BAMBOO_JUNGLE_HILLS,
                Biome.BAMBOO_JUNGLE, Biome.BAMBOO_JUNGLE_HILLS, Biome.MODIFIED_JUNGLE, Biome.MODIFIED_JUNGLE_EDGE);
        if (biomes.contains(player.getLocation().getBlock().getBiome())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 340, 1,
                    false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 340, 1,
                    false, false, true));
            return true;
        }
        return false;
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add(" ");
        lore.add(ChatColor.GREEN + "A gem that contains the spirit of");
        lore.add(ChatColor.GREEN + "the jungle. You can feel it pulse,");
        lore.add(ChatColor.GREEN + "like a beating heart.");
        return lore;
    }

}
