package net.obesephoenix.koalakraftcrystals.commands

import net.obesephoenix.koalakraftcrystals.commands.KKCommand
import net.obesephoenix.koalakraftcrystals.util.KKMessage
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import org.bukkit.command.CommandSender
import java.util.ArrayList

class DefaultCommand : KKCommand(
    "default", "default_command", "/kkc",
    "koalakraftcrystals.default"
) {
    override fun execute(sender: CommandSender, vararg args: String): Boolean {
        sender.sendMessage(
            KKMessage.format(
                "default-command", arrayOf(
                    KoalaKraftCrystals.instance
                        .description.version
                )
            )
        )
        return true
    }

    override fun tabComplete(sender: CommandSender, vararg args: String): List<String> {
        return ArrayList()
    }
}