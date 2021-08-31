package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.crystals.Crystal;
import net.obesephoenix.koalakraftcrystals.crystals.KKCrystalHandler;
import net.obesephoenix.koalakraftcrystals.util.KKFileUtil;
import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DisableCrystalCommand extends KKCommand {

    public DisableCrystalCommand() {
        super("disablecrystal", "disablecrystal_command", "/kkc disablecrystal <crystal>",
                "koalakraftcyrstals.disablecrystal");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        if (args.length < 2) {
            sender.sendMessage(KKMessage.format("error.invalid-args", new Object[0], false));
            return true;
        }

        Crystal crystal = KKCrystalHandler.getCrystalByID(args[1]);
        if (crystal == null) {
            sender.sendMessage(KKMessage.format("error.no-crystal", new Object[]{args[1]}, false));
            return true;
        }

        KKFileUtil.getConfigFile().get().set(crystal.getID() + ".enabled", false);
        KKFileUtil.getConfigFile().saveConfig();
        KKCrystalHandler.registerCrystals();

        List<String> messages = KKMessage.formatMultiline("disablecrystal-command.success", new Object[]{crystal.getID()});
        messages.forEach(sender::sendMessage);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String... args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.getCrystalIDs(), options);
        }

        return options;
    }

}
