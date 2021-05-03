package me.minemis.customtimespeed.commands;

import me.minemis.customtimespeed.CustomTimeSpeed;
import me.minemis.customtimespeed.system.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimeSpeedCommand implements CommandExecutor {

    private final TimeUtils timeUtils;

    long value;

    public TimeSpeedCommand(CustomTimeSpeed plugin) {
        timeUtils = new TimeUtils(plugin);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0){
            sender.sendMessage("Not a valid argument");
            return true;
        }

        timeUtils.cancelTask();

        if (args[0].equalsIgnoreCase("freeze")){
            setCycle(false);
            return true;
        }
        if (args[0].equalsIgnoreCase("normal")){
            setCycle(true);
            return true;
        }

        try {
            value = Long.parseLong(args[0]);
            setCycle(false);
        } catch (Exception e){
            sender.sendMessage("Not a valid argument");
            return true;
        }

        if(args.length < 2){
            timeUtils.timeChanger(value);
            return true;
        }

        if (args[1].equalsIgnoreCase("hours") || args[1].equalsIgnoreCase("hour")){
            timeUtils.timeChanger(value * 72000);
            return true;
        }
        if (args[1].equalsIgnoreCase("minutes" ) || args[1].equalsIgnoreCase("minute")){
            timeUtils.timeChanger(value * 1200);
            return true;
        }
        if (args[1].equalsIgnoreCase("secounds") || args[1].equalsIgnoreCase("secound")){
            timeUtils.timeChanger(value * 20);
            return true;
        }

        sender.sendMessage("Not a valid argument");
        return true;
    }

    public void setCycle(boolean is){
        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("doDaylightCycle", String.valueOf(is));
        }
    }
}
