package net.obesephoenix.koalakraftcrystals.util;

import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class KKTabCompletion {

    public static List<String> getOnlinePlayers() {
        List<String> players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(p -> players.add(p.getDisplayName()));
        return players;
    }

    public static List<String> getCrystals() {
        List<String> crystals = new ArrayList<>();
        KKCrystalHandler.getCrystals().forEach(p -> crystals.add(p.getName()));
        return crystals;
    }

}
