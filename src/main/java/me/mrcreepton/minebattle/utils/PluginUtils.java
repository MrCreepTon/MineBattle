package me.mrcreepton.minebattle.utils;

import me.mrcreepton.minebattle.Main;
import org.bukkit.Bukkit;

public class PluginUtils {

    public static Main getMainInstance()
    {
        return (Main) Bukkit.getPluginManager().getPlugin("MineBattle");
    }

}
