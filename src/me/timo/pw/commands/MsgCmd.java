package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.sendmessage;
import me.timo.timoapi.sound.playsound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MsgCmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    if(target.getName() != player.getName()) {
                        String message = ChatColor.translateAlternateColorCodes('&', args[1]);
                        player.sendMessage("§8§l[§6" + player.getName() + " §8§l→ §6" + target.getName() + "§8§l]§3 " + message);
                        target.sendMessage("§8§l[§6" + player.getName() + " §8§l→ §6" + target.getName() + "§8§l]§3 " + message);
                        playsound.Pling(target);
                    } else {
                        player.sendMessage(prefix.error() + "Du kannst dir nicht selber schreiben!");
                    }
                } else {
                    sendmessage.wrongplayer(player);
                }
            } else {
                sendmessage.wrongusage(player);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final  List <String> result = new ArrayList<>();

        if(args.length == 1) {
            result.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
            Player player = (Player) sender;
            result.remove(player.getName());
        } else if(args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                Collections.addAll(result, "Deine Nachricht");
            } else {
                Collections.addAll(result, "§c§lSpieler nicht gefunden!");
            }
        }
        return result;
    }
}
