package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class RubyCrystal extends Crystal {

    public RubyCrystal() {
        super("ruby",
                "ruby_crystal",
                "http://textures.minecraft.net/texture/c20ef06dd60499766ac8ce15d2bea41d2813fe55718864b52dc41cbaae1ea913",
                "nether",
                Arrays.asList("Fire Resistance", "Resistance 2"),
                ChatColor.RED,
                ChatColor.DARK_RED);
    }

    @Override
    public void grantEffects(Player player) {
        List<Biome> biomes = Arrays.asList(Biome.NETHER_WASTES, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST, Biome.SOUL_SAND_VALLEY,
                Biome.BASALT_DELTAS);
        if (biomes.contains(player.getLocation().getBlock().getBiome())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 340, 0,
                    false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 340, 1,
                    false, false, true));
        }
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add(" ");
        lore.add(ChatColor.RED + "A gem forged from the fiery");
        lore.add(ChatColor.RED + "depths of hell. You can still feel");
        lore.add(ChatColor.RED + "the heat emanating from its core.");
        return lore;
    }
}
