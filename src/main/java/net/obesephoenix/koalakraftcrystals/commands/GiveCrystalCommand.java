package net.obesephoenix.koalakraftcrystals.commands;

import org.bukkit.command.CommandSender;

public class GiveCrystalCommand extends KKCommand {

    public GiveCrystalCommand() {
        super("givecrystal", "givecrystal_command", "/kkc givecrystal <player> <crystal> <amount >");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        return false;
    }

}
