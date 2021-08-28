package net.obesephoenix.koalakraftcrystals.crystals;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class KKCrystalHandler {

    private static final List<Crystal> crystals = new ArrayList<>();

    public static void registerCrystals() {
        registerCrystal(new TopazCrystal());
        registerCrystal(new SapphireCrystal());
        registerCrystal(new OnyxCrystal());
        registerCrystal(new EmeraldCrystal());
        registerCrystal(new RubyCrystal());

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getInventory().forEach(item -> {
                        if (item == null) return;
                        ItemMeta meta = item.getItemMeta();
                        if(meta == null) return;

                        if(meta.getPersistentDataContainer().has(new NamespacedKey(KoalaKraftCrystals.instance,
                                "crystal_id"), PersistentDataType.STRING)) {
                            String id = meta.getPersistentDataContainer().get(new NamespacedKey(KoalaKraftCrystals.instance,
                                    "crystal_id"), PersistentDataType.STRING);
                            Crystal crystal = KKCrystalHandler.getCrystalByID(id);

                            assert crystal != null;
                            if (crystal.grantEffects(player)) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 340, 1,
                                        false, false, true));
                            }
                        }
                    });
                }
            }
        }.runTaskTimer(KoalaKraftCrystals.instance, 0L, 120L);
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
