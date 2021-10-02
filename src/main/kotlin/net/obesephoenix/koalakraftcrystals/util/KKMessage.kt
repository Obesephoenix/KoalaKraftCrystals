package net.obesephoenix.koalakraftcrystals.util

import org.bukkit.configuration.file.FileConfiguration
import kotlin.jvm.JvmOverloads
import org.bukkit.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import java.io.InputStreamReader
import java.util.*
import java.util.function.Consumer

object KKMessage {
    private val messageFile: FileConfiguration = YamlConfiguration.loadConfiguration(
        InputStreamReader(
            Objects.requireNonNull(KKMessage::class.java.getResourceAsStream("/en-us.yml"))
        )
    )

    @JvmOverloads
    fun format(key: String, args: Array<Any> = arrayOf(), prefix: Boolean = true): String {
        var message = if (prefix) messageFile.getString("prefix") + " " + messageFile.getString(
            key
        ) else messageFile.getString(key)
        if (message == null) return "null"
        for (c in args.indices) {
            message = message!!.replace("[$c]", args[c].toString())
        }
        return ChatColor.translateAlternateColorCodes('&', message!!)
    }

    @JvmOverloads
    fun formatMultiline(key: String?, args: Array<Any?> = arrayOfNulls(0)): List<String> {
        val messages = messageFile.getStringList(key!!)
        val others: MutableList<String> = ArrayList()
        messages.forEach(Consumer { m: String ->
            var message = m
            for (c in args.indices) {
                message = message.replace("[$c]", args[c].toString())
            }
            message = ChatColor.translateAlternateColorCodes('&', message)
            others.add(message)
        })
        return others
    }
}