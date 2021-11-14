package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.send;
import me.timo.timoapi.send.sendmessage;
import me.timo.timoapi.sound.playsound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetPositionCmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                File file = new File("plugins/SurvivalWorld/positions", player.getName().toString() + ".yml");
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                if(!cfg.contains(args[0])) {
                    String world = player.getWorld().getName();
                    double x = player.getLocation().getX();
                    double y = player.getLocation().getY();
                    double z = player.getLocation().getZ();

                    cfg.set(args[0] + ".world", world);
                    cfg.set(args[0] + ".x", x);
                    cfg.set(args[0] + ".y", y);
                    cfg.set(args[0] + ".z", z);
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    player.sendMessage(prefix.succes() + "Deine Position §6" + args[0] + " §awurde erstellt!");
                    send.title(player, "§a§lPosition Gesetzt", "§6§l" + args[0]);
                    playsound.Playerlevelup(player);
                } else {
                    player.sendMessage(prefix.error() + "Du besitzt schon eine Position mit dem Namen §6" + args[0]);
                    send.title(player, "§c§lFehler", "§c§lPosition Existiert schon!");
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
            Collections.addAll(result,"§6§lDein Home Name");
        }
        return result;
    }
}
