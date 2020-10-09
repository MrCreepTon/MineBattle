package me.mrcreepton.minebattle.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

    private static String tag = "§b§l[Mine§6§lBattle]";

    public static void sendMessage(CommandSender sender, String message)
    {
        sender.sendMessage(tag + ": §f" + message);
    }

    public static void sendMessage(Player sender, String message)
    {
        sender.sendMessage(tag + ": §f" + message);
    }

    public static void broadcastMessage(String message)
    {
        Bukkit.getServer().broadcastMessage(tag + ": §f" + message);
    }

}
