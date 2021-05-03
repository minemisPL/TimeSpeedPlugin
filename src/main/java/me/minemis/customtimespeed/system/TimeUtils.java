package me.minemis.customtimespeed.system;

import me.minemis.customtimespeed.CustomTimeSpeed;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class TimeUtils {
    private final CustomTimeSpeed plugin;
    private int taskID;

    public TimeUtils(CustomTimeSpeed plugin) {
        this.plugin = plugin;
    }

    public void timeChanger(long time){

        double gameSpeed = 24000D / time;

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable() {

            double ticks = Bukkit.getWorlds().get(0).getTime();

            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    world.setTime((long) (ticks + gameSpeed));
                }
                ticks += gameSpeed;
            }
        },0L, 1L);

    }
    public void cancelTask(){
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
