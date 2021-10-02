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

public class SapphireCrystal extends Crystal {

    public SapphireCrystal() {
        super("sapphire",
                "sapphire_crystal",
                "http://textures.minecraft.net/texture/5ba338334ec4c04a304a083a0a05695d5038eef6a5fadf0fdd6b6e1d9c340235",
                "ocean",
                Arrays.asList("Dolphins Grace", "Water Breathing"),
                ChatColor.AQUA,
                ChatColor.BLUE);
    }

    @Override
    public boolean grantEffects(Player player) {
        List<Biome> biomes = Arrays.asList(Biome.OCEAN, Biome.COLD_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.DEEP_FROZEN_OCEAN,
                Biome.DEEP_LUKEWARM_OCEAN, Biome.DEEP_OCEAN, Biome.WARM_OCEAN, Biome.LUKEWARM_OCEAN, Biome.DEEP_WARM_OCEAN,
                Biome.WARM_OCEAN, Biome.FROZEN_OCEAN);
        if(biomes.contains(player.getLocation().getBlock().getBiome())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 340, 0,
                    false , false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 340, 0,
                    false, false, true));
            return true;
        }
        return false;
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add(" ");
        lore.add(ChatColor.AQUA + "A gem as blue as the sea itself. If");
        lore.add(ChatColor.AQUA + "you listen closely, you can hear the");
        lore.add(ChatColor.AQUA + "waves on the beach against the shore.");
        return lore;
    }

}
