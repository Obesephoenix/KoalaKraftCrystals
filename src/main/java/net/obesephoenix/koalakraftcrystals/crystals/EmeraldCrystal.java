package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
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
    public void grantEffects(Player player) {

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
