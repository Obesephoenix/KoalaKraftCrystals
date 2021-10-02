package net.obesephoenix.koalakraftcrystals.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStreamReader;
import java.util.*;

public class KKMessage {

    private static final FileConfiguration messageFile = YamlConfiguration.loadConfiguration(new InputStreamReader(
            Objects.requireNonNull(KKMessage.class.getResourceAsStream("/en-us.yml"))));

    public static String format(String key, Object[] args) {return format(key, args, true);}
    public static String format(String key) {return format(key, new Object[0], true);}
    public static String format(String key, Object[] args, boolean prefix) {
        String message = prefix ?  messageFile.getString("prefix")  + " " + messageFile.getString(key)
                : messageFile.getString(key);
        if (message == null) return "null";

        for (int c=0;c<args.length;c++) {
            message = message.replace('[' + String.valueOf(c) + ']', String.valueOf(args[c]));
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> formatMultiline(String key) {return formatMultiline(key, new Object[0]);}
    public static List<String> formatMultiline(String key, Object[] args) {
        List<String> messages = messageFile.getStringList(key);
        List<String> others = new ArrayList<>();

        messages.forEach(message -> {
            for (int c=0;c<args.length;c++) {
                message = message.replace('[' + String.valueOf(c) + ']', String.valueOf(args[c]));
            }

            message = ChatColor.translateAlternateColorCodes('&', message);
            others.add(message);
        });
        
        return others;
    }

}
