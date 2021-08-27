package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
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
                "http://textures.minecraft.net/texture/b91aeca7c17e66d867231b36d96e83c1ede75eaf67ccf3a88dca15d4114ae167",
                "ocean",
                Arrays.asList("Dolphins Grace", "Water Breathing"),
                ChatColor.AQUA,
                ChatColor.BLUE);
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
