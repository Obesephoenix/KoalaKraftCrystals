package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnyxCrystal extends Crystal {

    public OnyxCrystal() {
        super("onyx",
                "onyx_crystal",
                "http://textures.minecraft.net/texture/188bce497cfa5f611839f6da21c95d34e3e723c2cc4c3c319b562773d1216",
                Biome.THE_END,
                getDefaultEffects(),
                ChatColor.DARK_PURPLE,
                ChatColor.LIGHT_PURPLE);
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add("");
        lore.add(ChatColor.DARK_PURPLE + "A pure black gem. Staring into");
        lore.add(ChatColor.DARK_PURPLE + "its depths you can't help but feel");
        lore.add(ChatColor.DARK_PURPLE + "an overwhelming sense of dread...");
        return lore;
    }

    @Override
    protected String getBiomeName(Biome biome) {
        return "END";
    }

    private static Map<PotionEffectType, Integer> getDefaultEffects() {
        Map<PotionEffectType, Integer> effects = new HashMap<>();
        effects.put(PotionEffectType.INVISIBILITY, 1);
        effects.put(PotionEffectType.SLOW_FALLING, 1);
        return effects;
    }

}
