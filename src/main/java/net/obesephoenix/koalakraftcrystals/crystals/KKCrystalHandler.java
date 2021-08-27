package net.obesephoenix.koalakraftcrystals.crystals;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class KKCrystalHandler {

    private static final List<Crystal> crystals = new ArrayList<>();

    public static void registerCrystals() {
        registerCrystal(new TopazCrystal());
        registerCrystal(new SapphireCrystal());
    }

    private static void registerCrystal(Crystal crystal) {
        crystals.add(crystal);
    }

    public static List<Crystal> getCrystals() {return crystals;}

    public static Crystal getCrystal(String name) {
        for(Crystal crystal : crystals) {
            if (crystal.getName().equalsIgnoreCase(name)) {
                return crystal;
            }
        }
        return null;
    }

    public static Crystal getCrystalByID(String id) {
        for(Crystal crystal : crystals) {
            if (crystal.getID().equalsIgnoreCase(id)) {
                return crystal;
            }
        }
        return null;
    }

}
