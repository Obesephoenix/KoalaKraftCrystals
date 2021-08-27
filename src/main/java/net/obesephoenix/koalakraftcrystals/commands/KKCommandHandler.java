package net.obesephoenix.koalakraftcrystals.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KKCommandHandler implements CommandExecutor {

    private static final List<KKCommand> commands = new ArrayList<>();

    public static void registerCommands() {
        registerCommand(new DefaultCommand());
    }

    public static void registerCommand(KKCommand command) {
        commands.add(command);
    }

    public static KKCommand getCommandByID(String id) {
        for (KKCommand command : commands) {
            if (command.getID().equalsIgnoreCase(id)) {
                return command;
            }
        }
        return null;
    }

    public static KKCommand getCommand(String name) {
        for (KKCommand command : commands) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!label.equals("kkc")) return false;

        for (KKCommand c : commands) {
            if (label.equalsIgnoreCase(c.getName())) {
                return c.execute(commandSender, args);
            }
        }

        return getCommand("default").execute(commandSender, args);
    }

}
