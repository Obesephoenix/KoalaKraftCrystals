package net.obesephoenix.koalakraftcrystals.util;

import net.obesephoenix.koalakraftcrystals.commands.KKCommandHandler;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class KKTabCompletionUtil {

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

    public static List<String> getCrystalIDs() {
        List<String> ids = new ArrayList<>();
        KKCrystalHandler.getCrystals().forEach(p -> ids.add(p.getID()));
        return ids;
    }

    public static List<String> getCommands() {
        List<String> commands = new ArrayList<>();
        KKCommandHandler.getCommands().forEach(p -> {
            if (!p.getName().equalsIgnoreCase("default")) {
                commands.add(p.getName());
            }
        });
        return commands;
    }

}
