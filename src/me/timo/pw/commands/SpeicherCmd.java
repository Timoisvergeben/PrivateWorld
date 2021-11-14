package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.sendmessage;
import me.timo.timoapi.sound.playsound;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeicherCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                Bukkit.getServer().savePlayers();
                Bukkit.getWorld("world").save();
                Bukkit.getWorld("world_nether").save();
                Bukkit.getWorld("world_the_end").save();
                player.sendMessage(prefix.succes() + "Die Welt wurde gespeichert!");
                playsound.Playerlevelup(player);
            } else {
                sendmessage.wrongusage(player);
            }
        }
        return false;
    }
}
