package net.obesephoenix.koalakraftcrystals.commands;

import net.obesephoenix.koalakraftcrystals.util.KKMessage;
import net.obesephoenix.koalakraftcrystals.util.KKTabCompletionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ReverseNetheriteCommand extends KKCommand {

    public ReverseNetheriteCommand() {
        super("reversenetherite", "reversenetherite_command", "/kkc reversenetherite <player>",
                "koalakraftcrystals.reversenetherite");
    }

    @Override
    public boolean execute(CommandSender sender, String... args) {
        if (args.length != 2) {
            sender.sendMessage(KKMessage.format("error.invalid-args", new Object[0], false));
            return false;
        }

        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(KKMessage.format("error.no-player", new Object[]{args[1]}, false));
            return true;
        }

        player.getInventory().forEach(item -> {
            if (item == null) return;
            if (item.getType().toString().toLowerCase().contains("netherite")) {
                Material mat = Material.getMaterial(item.getType().toString().replace("NETHERITE", "DIAMOND"));
                if (mat == null) return;
                item.setType(mat);
            }
        });

        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 3.0f, 1.0f);
        List<String> var1 = KKMessage.formatMultiline("reversenetherite-command.success", new Object[]{player.getDisplayName()});
        var1.forEach(sender::sendMessage);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String... args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], KKTabCompletionUtil.getOnlinePlayers(), options);
        }

        return options;
    }

}
