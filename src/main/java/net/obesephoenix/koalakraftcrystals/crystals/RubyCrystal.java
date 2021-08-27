package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class RubyCrystal extends Crystal {

    public RubyCrystal() {
        super("ruby",
                "ruby_crystal",
                "http://textures.minecraft.net/texture/f83236639607036c1ba391c2b46a9c7b0efd760c8bfa2996a6055582b4da5",
                "nether",
                Arrays.asList("Fire Resistance", "Resistance 2"),
                ChatColor.RED,
                ChatColor.DARK_RED);
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
