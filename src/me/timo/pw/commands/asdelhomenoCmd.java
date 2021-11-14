package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.sendmessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class asdelhomenoCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(prefix.succes() + "Du hast deine Position nicht gelöscht!");
            } else {
                sendmessage.wrongusage(player);
            }
        }
        return false;
    }
}
