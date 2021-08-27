package net.obesephoenix.koalakraftcrystals.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class KKTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<>();

        if (label.equalsIgnoreCase("kkc")) {
            if (args.length != 0) {
                for (KKCommand cmd : KKCommandHandler.getCommands()) {
                    if (args[0].equalsIgnoreCase(cmd.getName())) {
                        options = cmd.tabComplete(commandSender, args);
                    }
                }
            }
        }

        return options;
    }
}
