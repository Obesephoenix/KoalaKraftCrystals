package net.obesephoenix.koalakraftcrystals.crystals;

import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GodModeHandler {

    private static final List<Player> godPlayers = new ArrayList<>();

    public static void setGodMode(Player player) {setGodMode(player, true);}
    public static void setGodMode(Player player, boolean val) {
        if (val) {
            godPlayers.add(player);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        } else {
            godPlayers.remove(player);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        }
    }

    public static boolean isGodMode(Player player) {
        return godPlayers.contains(player);
    }

}
