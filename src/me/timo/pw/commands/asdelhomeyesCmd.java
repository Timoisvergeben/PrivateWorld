package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.send;
import me.timo.timoapi.send.sendmessage;
import me.timo.timoapi.sound.playsound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class asdelhomeyesCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                File file = new File("plugins/SurvivalWorld/positions", player.getName().toString() + ".yml");
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                if(cfg.contains(args[0])) {
                    cfg.set(args[0], null);
                    cfg.set(args[0] + ".world", null);
                    cfg.set(args[0] + ".x", null);
                    cfg.set(args[0] + ".y", null);
                    cfg.set(args[0] + ".z", null);
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    player.sendMessage(prefix.succes() + "§cDeine Position §6" + args[0] + "§c wurde gelöscht!");
                    send.title(player, "§c§lPosition gelöscht", "§6§lHome: " + args[0]);
                    playsound.WitherBreakBlock(player);
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
}
