package net.obesephoenix.koalakraftcrystals.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class KKCommand {

    private final String name;
    private final String id;
    private final String usage;
    
    public KKCommand(String name, String id, String usage) {
        this.name = name;
        this.id = id;
        this.usage = usage;
    }

    public String getName() {return name;}
    public String getID() {return id;}
    public String getUsage() {return usage;}

    public abstract boolean execute(CommandSender sender, String... args);
    public abstract List<String> tabComplete(CommandSender sender, String... args);

}
