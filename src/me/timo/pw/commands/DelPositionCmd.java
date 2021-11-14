package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.send;
import me.timo.timoapi.send.sendmessage;
import me.timo.timoapi.sound.playsound;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DelPositionCmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1) {
                File file = new File("plugins/SurvivalWorld/positions", player.getName().toString() + ".yml");
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                if(cfg.contains(args[0])) {
                    player.sendMessage("");
                    player.sendMessage("§8§m---------");
                    player.sendMessage("§6§lPosition Löschen");
                    player.sendMessage("");
                    player.sendMessage("§aPosition: §6" + args[0]);
                    player.sendMessage("");
                    sendmessage.clickable(player, "§8[§aAbbruch§8]", "asdelhomeno", false, ChatColor.GREEN, "§a§lPosition nicht Löschen");
                    sendmessage.clickable(player, "§8[§cLöschen§8]", "asdelhomeyes " + args[0], false, ChatColor.RED, "§c§lPosition Löschen");
                    player.sendMessage("§8§m---------");
                } else {
                    player.sendMessage(prefix.error() + "Du hast keine Position mit dem Namen §6" + args[0]);
                    send.title(player, "§c§lFehler", "§c§lPosition nicht gefunden");
                    playsound.WitherBreakBlock(player);
                }
            } else {
                sendmessage.wrongusage(player);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final List<String> result = new ArrayList<>();
        if(args.length == 1) {
            Player player = (Player) sender;
            File file = new File("plugins/SurvivalWorld/positions", player.getName().toString() + ".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            result.addAll(configuration.getKeys(false));
        }
        return result;
    }
}
