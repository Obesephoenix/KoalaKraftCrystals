package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.crystals.Crystal;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CrystalInfoCommand extends KKCommand {

    public CrystalInfoCommand() {
        super("crystalinfo", "crystalinfo_command", "/kkc crystalinfo <player>",
                "koalakraftcrystals.crystalinfo");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        if (args.length < 2) {
            sender.sendMessage(KKMessage.format("error.invalid-args", new Object[0], false));
            return false;
        }

        Player player = Bukkit.getPlayer(args[1]);
        Crystal crystal = KKCrystalHandler.getCrystalByID(args[1]);
        boolean usePlayer;
        if (crystal == null) {
            if (player != null) {
                usePlayer = true;
            } else {
                sender.sendMessage(KKMessage.format("error.no-crystal", new Object[]{args[1]}, false));
                return true;
            }
        } else {
            usePlayer = false;
        }

        if (usePlayer) {
            List<Crystal> crystals = KKCrystalHandler.getCrystalsFromPlayer(player);

            if (crystals.size() != 0) {
                sender.sendMessage(KKMessage.format("crystalinfo-command.success.player.found", new Object[]{player.getDisplayName()}));
                crystals.forEach(c -> sender.sendMessage(ChatColor.GREEN + " - " + WordUtils.capitalize(c.getName())));
            } else {
                sender.sendMessage(KKMessage.format("crystalinfo-command.success.player.none", new Object[]{player.getDisplayName()}));
            }
            return true;
        } else {
            List<Player> targets = KKCrystalHandler.getPlayerFromCrystal(crystal);

            if (targets.size() == 0) {
                sender.sendMessage(KKMessage.format("crystalinfo-command.success.crystal.none", new Object[]{crystal.getID()}));
            } else {
                sender.sendMessage(KKMessage.format("crystalinfo-command.success.crystal.found", new Object[]{crystal.getID()}));
                targets.forEach(target -> sender.sendMessage(ChatColor.GREEN + " - " + target.getDisplayName()));
            }
            return true;
        }
    }

        

    @Override
    public List<String> tabComplete(CommandSender sender, String... args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.getCrystalIDs(), options);
        }

        return options;
    }

}
