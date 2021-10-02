package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class DefaultCommand extends KKCommand {

    public DefaultCommand() {
        super("default", "default_command", "/kkc",
                "koalakraftcrystals.default");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        sender.sendMessage(KKMessage.format("default-command", new Object[]{KoalaKraftCrystals.instance
                .getDescription().getVersion()}));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String... args) {
        return new ArrayList<String>();
    }

}
