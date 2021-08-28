package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.crystals.Crystal;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class GiveCrystalCommand extends KKCommand {

    public GiveCrystalCommand() {
        super("givecrystal", "givecrystal_command", "/kkc givecrystal <player> <crystal> <amount>",
                "koalakraftcrystals.givecrystal");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        if (args.length < 3) {
            sender.sendMessage(KKMessage.format("error.invalid-args", new Object[0], false));
            return true;
        }

        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(KKMessage.format("error.no-player", new Object[]{args[1]}, false));
            return true;
        }

        if (args[2].equalsIgnoreCase("all")) {
            KKCrystalHandler.getCrystals().forEach(crystal -> player.getInventory().addItem(crystal.createItemStack()));
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
            sender.sendMessage(KKMessage.format("givecrystal-command.success.all", new Object[]{player.getDisplayName()}));
            return true;
        }

        Crystal crystal = KKCrystalHandler.getCrystalByID(args[2]);
        if (crystal == null) {
            sender.sendMessage(KKMessage.format("givecrystal-command.error.no-crystal", new Object[]{args[2]}, false));
            return true;
        }

        int amount;
        if (args.length == 4) {
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage(KKMessage.format("error.invalid-int", new Object[]{args[3]}, false));
                sender.sendMessage(ChatColor.DARK_RED + this.getUsage());
                return true;
            }
        } else {
            amount = 1;
        }

        for (int c=0;c<amount;c++) {
            player.getInventory().addItem(crystal.createItemStack());
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
        sender.sendMessage(KKMessage.format("givecrystal-command.success.single", new Object[]{crystal.getID(),
        amount, player.getDisplayName()}));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String... args) {
        List<String> options = new ArrayList<>();

        switch (args.length) {
            case 2:
                StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.getOnlinePlayers(), options);
                break;
            case 3:
                List<String> ids = KKTabCompletionUtil.getCrystalIDs();
                ids.add("all");
                StringUtil.copyPartialMatches(args[2], ids, options);
                break;
        }

        return options;
    }

}
