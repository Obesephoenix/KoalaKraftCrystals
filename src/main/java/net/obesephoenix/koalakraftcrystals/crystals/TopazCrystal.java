package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopazCrystal extends  Crystal {

    public TopazCrystal() {
        super("topaz",
                "topaz_crystal",
                "http://textures.minecraft.net/texture/e7412ae75b822ce9c9ad4cbae875061b6bf3de2333ab137c82d661dd9bef",
                Biome.DESERT,
                getDefaultEffects(),
                ChatColor.GOLD,
                ChatColor.YELLOW);
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add(" ");
        lore.add(ChatColor.GOLD + "A golden gem that shines as bright");
        lore.add(ChatColor.GOLD + "as the sun. It's warm glow fills");
        lore.add(ChatColor.GOLD + "you with strength.");
        return lore;
    }

    private static Map<PotionEffectType, Integer> getDefaultEffects() {
        Map<PotionEffectType, Integer> effects = new HashMap<>();
        effects.put(PotionEffectType.REGENERATION, 2);
        effects.put(PotionEffectType.SATURATION, 3);
        return effects;
    }

}
