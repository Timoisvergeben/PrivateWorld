package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.sendmessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(prefix.succes() + "Die Online Map findest du unter");
                player.sendMessage(prefix.succes() + "");
            } else {
                sendmessage.wrongusage(player);
            }
        }
        return false;
    }
}
