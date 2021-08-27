package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class TopazCrystal extends  Crystal {

    public TopazCrystal() {
        super("Topaz", "topaz_crystal",
                "http://textures.minecraft.net/texture/e7412ae75b822ce9c9ad4cbae875061b6bf3de2333ab137c82d661dd9bef"
                , getDefaultEffects());
    }

    private static Map<PotionEffectType, Integer> getDefaultEffects() {
        Map<PotionEffectType, Integer> effects = new HashMap<>();
        effects.put(PotionEffectType.REGENERATION, 2);
        effects.put(PotionEffectType.SATURATION, 3);
        return effects;
    }

}
