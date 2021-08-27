package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import org.bukkit.command.CommandSender;

public class DefaultCommand extends KKCommand {

    public DefaultCommand() {
        super("default", "default_command", "/kkc");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        sender.sendMessage(KKMessage.format("default-command", new Object[]{KoalaKraftCrystals.instance
                .getDescription().getVersion()}));
        return true;
    }

}
