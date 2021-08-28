package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class TopazCrystal extends  Crystal {

    public TopazCrystal() {
        super("topaz",
                "topaz_crystal",
                "http://textures.minecraft.net/texture/1847fd5517c7de90e88c4aa6acbe250ed42e928d46a653b81e0956e459e8295a",
                "desert",
                Arrays.asList("Regeneration 2", "Saturation 3"),
                ChatColor.GOLD,
                ChatColor.YELLOW);
    }

    @Override
    public void grantEffects(Player player) {
        Bukkit.broadcastMessage("effect granted");
    }

    @Override
    protected List<String> addLorePrefix(List<String> lore) {
        lore.add(" ");
        lore.add(ChatColor.GOLD + "A golden gem that shines as bright");
        lore.add(ChatColor.GOLD + "as the sun. It's warm glow fills");
        lore.add(ChatColor.GOLD + "you with strength.");
        return lore;
    }

}
