package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class TopazCrystal extends  Crystal {

    public TopazCrystal(Map<PotionEffectType, Integer> effects) {
        super("Topaz", "topaz_crystal",
                "http://textures.minecraft.net/texture/e8c511b961b2dca012f3a5f5b466078a0ec2380a76d519ea364a7d6dc28e1bb"
                , effects);
    }

}
