package me.minemis.customtimespeed;

import me.minemis.customtimespeed.commands.TimeSpeedCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomTimeSpeed extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("timespeed").setExecutor(new TimeSpeedCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
