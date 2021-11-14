package me.timo.pw.main;

import me.timo.pw.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("§8§l[§c§lTimo§8§l] §aDas Plugin §bPrivate World §awurde geladen!");

        //Commands
        getCommand("speicher").setExecutor(new SpeicherCmd());
        getCommand("msg").setExecutor(new MsgCmd());
        getCommand("map").setExecutor(new MapCmd());

        //Tab Completer
        getCommand("msg").setExecutor(new MsgCmd());

        //Position Register
        getCommand("asdelhomeno").setExecutor(new asdelhomenoCmd());
        getCommand("asdelhomeyes").setExecutor(new asdelhomeyesCmd());
        getCommand("position").setExecutor(new PositionCmd());
        getCommand("delposition").setExecutor(new DelPositionCmd());
        getCommand("setposition").setExecutor(new SetPositionCmd());
        getCommand("position").setTabCompleter(new PositionCmd());
        getCommand("delposition").setTabCompleter(new DelPositionCmd());
        getCommand("setposition").setTabCompleter(new SetPositionCmd());
    }
}
