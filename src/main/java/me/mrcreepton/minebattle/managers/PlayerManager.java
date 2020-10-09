package me.mrcreepton.minebattle.managers;

import me.mrcreepton.minebattle.Main;
import me.mrcreepton.minebattle.configs.PlayerConfig;
import me.mrcreepton.minebattle.models.Player;
import me.mrcreepton.minebattle.models.Task;
import me.mrcreepton.minebattle.utils.ChatUtils;
import me.mrcreepton.minebattle.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private static List<Player> playerList = new ArrayList<>();

    public static void init()
    {
        PlayerConfig playerConfig = new PlayerConfig(PluginUtils.getMainInstance());
        Configuration config = playerConfig.getConfig();

        List<Task> taskList = TaskManager.getTaskList();
        if (config.contains("players"))
        {
            for (String playerName : config.getConfigurationSection("players").getKeys(false))
            {
                Player player = new Player(playerName);
                playerList.add(player);

                for (Task task : taskList)
                {
                    if (config.contains("players." + playerName + "." + task.getName()))
                    {
                        PlayerManager.setPointsToPlayerTask(playerName, task.getName(), config.getInt("players." + playerName + "." + task.getName()));
                    }
                }
            }
        }
    }

    public static void savePlayerConfig()
    {
        PlayerConfig playerConfig = new PlayerConfig(PluginUtils.getMainInstance());
        playerConfig.saveConfig();
    }

    public static void setPlayerConfigTask(String playerName, String taskName, int amount)
    {
        PlayerConfig playerConfig = new PlayerConfig(PluginUtils.getMainInstance());
        playerConfig.getConfig().set("players." + playerName + "." + taskName, amount);
        playerConfig.saveConfig();
    }

    public static Player getPlayerByNick(String name)
    {
        for (Player player : playerList)
        {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    public static List<Task> getPlayerTasks(String name)
    {
        for (Player player : playerList)
        {
            if (player.getName().equals(name))
                return player.getTaskList();
        }
        return null;
    }

    public static Task getPlayerTask(String name, String taskName)
    {
        for (Player player : playerList)
        {
            if (player.getName().equals(name))
            {
                for (Task task : player.getTaskList())
                {
                    if (task.getName().equalsIgnoreCase(taskName))
                        return task;
                }
            }
        }
        return null;
    }

    public static void registerNewPlayer(String name)
    {
        Player player = new Player(name);
        player.setImmunity(15);
        ChatUtils.sendMessage(Bukkit.getPlayer(name), "Вам выдан иммунитет на 15 секунд");
        playerList.add(player);
    }

    public static boolean removePointsFromPlayerTask(String name, String taskName, int amount)
    {
        Player player = getPlayerByNick(name);
        if (player != null)
        {
            for (Task task : player.getTaskList())
            {
                if (task.getName().equalsIgnoreCase(taskName))
                {
                    int points = task.getTotal() - amount;
                    if (points < 0)
                        points = 0;
                    task.setTotal(points);
                    setPlayerConfigTask(name, taskName, points);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean setPointsToPlayerTask(String name, String taskName, int amount)
    {
        Player player = getPlayerByNick(name);
        if (player != null)
        {
            for (Task task : player.getTaskList())
            {
                if (task.getName().equalsIgnoreCase(taskName))
                {
                    task.setTotal(amount);
                    setPlayerConfigTask(name, taskName, amount);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean addPointsToPlayerTask(String name, String taskName, int amount)
    {
        Player player = getPlayerByNick(name);
        if (player != null)
        {
            for (Task task : player.getTaskList())
            {
                if (task.getName().equalsIgnoreCase(taskName))
                {
                    task.setTotal(task.getTotal() + amount);
                    setPlayerConfigTask(name, taskName, task.getTotal());
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static Player getTopPlayerInTask(String taskName)
    {
        int most = -1;
        Player topPlayer = null;
        for (Player player : playerList)
        {
            Task task = getPlayerTask(player.getName(), taskName);
            if (task.getTotal() > most)
            {
                topPlayer = player;
                most = task.getTotal();
            }
        }
        return topPlayer;
    }

    public static boolean clearPointsFromPlayerTask(String name, String taskName)
    {
        Player player = getPlayerByNick(name);
        if (player != null)
        {
            for (Task task : player.getTaskList())
            {
                if (task.getName().equalsIgnoreCase(taskName))
                {
                    task.setTotal(0);
                    setPlayerConfigTask(name, taskName, 0);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean clearAllPlayerPoints(String name)
    {
        Player player = getPlayerByNick(name);
        if (player != null)
        {
            for (Task task : player.getTaskList())
            {
                task.setTotal(0);
                setPlayerConfigTask(name, task.getName(), 0);
            }
            return true;
        }
        return false;
    }


    public static List<Player> getPlayerList() {
        return playerList;
    }

    public static void setPlayerList(List<Player> playerList) {
        PlayerManager.playerList = playerList;
    }
}
