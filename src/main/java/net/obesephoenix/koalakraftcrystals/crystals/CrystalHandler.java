package net.obesephoenix.koalakraftcrystals.crystals;

import java.util.ArrayList;
import java.util.List;

public class CrystalHandler {

    private static final List<Crystal> crystals = new ArrayList<Crystal>();

    public static void registerCrystals() {

    }

    private static void registerCrystal(Crystal crystal) {
        crystals.add(crystal);
    }

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
