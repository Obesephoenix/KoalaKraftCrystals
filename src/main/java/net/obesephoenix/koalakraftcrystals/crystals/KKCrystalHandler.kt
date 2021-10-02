package net.obesephoenix.koalakraftcrystals.crystals;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import net.obesephoenix.koalakraftcrystals.util.KKFileUtil;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KKCrystalHandler {

    private static final List<Crystal> crystals = new ArrayList<>();
    public static final NamespacedKey crystalID_key = new NamespacedKey(KoalaKraftCrystals.instance, "crystal_id");

    public static void registerCrystals() {
        crystals.clear();

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

                        if(meta.getPersistentDataContainer().has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
                            String id = meta.getPersistentDataContainer().get(KKCrystalHandler.crystalID_key, PersistentDataType.STRING);
                            Crystal crystal = KKCrystalHandler.getCrystalByID(id);

                            assert crystal != null;
                            if (crystal.grantEffects(player)) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 340, 1,
                                        false, false, true));
                            }
                        }
                    });
                    List<Crystal> playerCrystals = KKCrystalHandler.getCrystalsFromPlayer(player);
                    if (playerCrystals.size() >= crystals.size()) {
                        if (!GodModeHandler.isGodMode(player)) {
                            GodModeHandler.setGodMode(player);
                        }
                    } else if (GodModeHandler.isGodMode(player)) {
                        GodModeHandler.setGodMode(player, false);
                    }
                }
            }
        }.runTaskTimer(KoalaKraftCrystals.instance, 0L, 120L);
    }

    private static void registerCrystal(Crystal crystal) {
        crystals.add(crystal);
    }


    public static List<Crystal> getCrystals() {
        List<Crystal> var1 = new ArrayList<>();
        crystals.forEach(crystal -> {
            if(!KKFileUtil.getConfigFile().get().getBoolean(crystal.getID() + ".enabled")) {
                return;
            }
            var1.add(crystal);
        });
        return var1;
    }

    public static Crystal getCrystal(String name) {
        for(Crystal crystal : crystals) {
            if (crystal.getName().equalsIgnoreCase(name)) {
                return crystal;
            }
        }
        return null;
    }

    /**
     *
     * Provides a list of all existing crystals, for most use cases use getCrystal()
     * @return A list of all existing crystals
     */
    public static List<Crystal> getRawCrystals() {
        return crystals;
    }

    public static Crystal getCrystalByID(String id) {
        for(Crystal crystal : crystals) {
            if (crystal.getID().equalsIgnoreCase(id)) {
                return crystal;
            }
        }
        return null;
    }

    public static List<Crystal> getCrystalsFromPlayer(Player player) {
        List<Crystal> playerCrystals = new ArrayList<>();

        player.getInventory().forEach(item -> {
            if (item == null) return;
            ItemMeta meta = item.getItemMeta();
            if (meta == null) return;

            if (meta.getPersistentDataContainer().has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
                Crystal crystal = KKCrystalHandler.getCrystalByID(meta.getPersistentDataContainer().get(KKCrystalHandler.crystalID_key,
                        PersistentDataType.STRING));
                playerCrystals.add(crystal);
            }
        });

        return playerCrystals;
    }

    public static List<Player> getPlayerFromCrystal(Crystal crystal) {
        List<Player> players = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            for (ItemStack i : p.getInventory()) {
                if (i == null) continue;
                ItemMeta meta = i.getItemMeta();
                if (meta == null) continue;

                if (meta.getPersistentDataContainer().has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING)) {
                    if (Objects.equals(meta.getPersistentDataContainer().get(KKCrystalHandler.crystalID_key, PersistentDataType.STRING), crystal.getID())) {
                        players.add(p);
                    }
                }
            }
        }

        return players;
    }

    public static boolean isCrystal(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().has(KKCrystalHandler.crystalID_key, PersistentDataType.STRING);
    }

}
