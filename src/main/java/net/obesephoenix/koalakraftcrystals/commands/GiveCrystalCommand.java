package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.crystals.Crystal;
import net.obesephoenix.koalakraftcrystals.crystals.CrystalHandler;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCrystalCommand extends KKCommand {

    public GiveCrystalCommand() {
        super("givecrystal", "givecrystal_command", "/kkc givecrystal <player> <crystal> <amount >");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(KKMessage.format("error.no-player", new Object[]{args[1]}));
            return false;
        }

        Crystal crystal = CrystalHandler.getCrystalByID(args[2]);
        if (crystal == null) {
            sender.sendMessage(KKMessage.format("givecrystal-command.error.no-crystal", new Object[]{args[2]}));
            return false;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[3]);
        } catch(NumberFormatException e) {
            sender.sendMessage(KKMessage.format("error.invalid-int", new Object[]{args[3]}));
            return false;
        }

        for (int c=0;c<amount;c++) {
            player.getInventory().addItem(crystal.createItemStack());
        }
        return true;
    }

}
