package me.timo.pw.commands;

import me.timo.timoapi.messages.prefix;
import me.timo.timoapi.send.sendmessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class PositionCmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
          Player player = (Player) sender;
          if(args.length == 1) {
              File file = new File("plugins/SurvivalWorld/positions", player.getName().toString() + ".yml");
              FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
              System.out.println(cfg.getKeys(false));
              if(cfg.contains(args[0])) {
                  String world = cfg.getString(args[0] + ".world");
                  double x = cfg.getDouble(args[0] + ".x");
                  double y = cfg.getDouble(args[0] + ".y");
                  double z = cfg.getDouble(args[0] + ".z");

                  Location loc = new Location(Bukkit.getWorld(world), x, y, z);

                  NumberFormat numberFormat = new DecimalFormat("0.0");
                  numberFormat.setRoundingMode(RoundingMode.DOWN);

                  player.sendMessage("");
                  player.sendMessage("§8§m-----§8§l[§6§lPosition§8§l]§8§m----");
                  player.sendMessage("§6Name: §a" + args[0]);
                  player.sendMessage("");
                  player.sendMessage("§6Welt: §a" + world);
                  player.sendMessage("§6X: §a" + numberFormat.format(x));
                  player.sendMessage("§6Y: §a" + numberFormat.format(y));
                  player.sendMessage("§6Z: §a" + numberFormat.format(z));
                  player.sendMessage("§8§m------------------");
              } else {
                  player.sendMessage(prefix.error() + "Du hast keine Position mit dem Namen " + args[0]);
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
