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
        if (player == null) {
            sender.sendMessage(KKMessage.format("error.no-player", new Object[]{args[1]}, false));
            return true;
        }

        List<Crystal> found = KKCrystalHandler.getCrystalsFromPlayer(player);
        if (found.size() == 0) {
            sender.sendMessage(KKMessage.format("crystalinfo-command.success.none", new Object[]{player.getDisplayName()}));
        } else {
            sender.sendMessage(KKMessage.format("crystalinfo-command.success.found", new Object[]{player.getDisplayName()}));
            found.forEach(c -> {
                sender.sendMessage(ChatColor.GREEN + "- " + WordUtils.capitalize(c.getName()));
            });
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String... args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.getOnlinePlayers(), options);
        }

        return options;
    }

}
