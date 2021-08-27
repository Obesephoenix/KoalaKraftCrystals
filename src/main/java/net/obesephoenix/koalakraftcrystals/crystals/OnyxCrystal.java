package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnyxCrystal extends Crystal {

    public OnyxCrystal() {
        super("onyx",
                "onyx_crystal",
                "http://textures.minecraft.net/texture/a79f8c92776d642d119f9e92360b1e5b971e66e61428a3e1b311d8b6185e6",
                "end",
                Arrays.asList("Slow Falling", "Invisibility"),
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

}
