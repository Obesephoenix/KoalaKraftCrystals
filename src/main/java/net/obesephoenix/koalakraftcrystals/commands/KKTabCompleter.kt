package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class KKTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<>();
        boolean found = false;

        if (label.equalsIgnoreCase("kkc")) {
            if (args.length != 0) {
                for (KKCommand cmd : KKCommandHandler.getCommands()) {
                    if (args[0].equalsIgnoreCase(cmd.getName())) {
                        options = cmd.tabComplete(commandSender, args);
                        found = true;
                    }
                }
                if (!found) StringUtil.copyPartialMatches(args[0], KKTabCompletionUtil.getCommands(), options);
            }
        }

        return options;
    }
}
