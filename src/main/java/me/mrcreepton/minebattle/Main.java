package me.mrcreepton.minebattle;

import me.mrcreepton.minebattle.commands.GetContactExecutor;
import me.mrcreepton.minebattle.commands.SetContactExecutor;
import me.mrcreepton.minebattle.commands.TopExecutor;
import me.mrcreepton.minebattle.commands.points.PointsExecutor;
import me.mrcreepton.minebattle.events.PlayerDeathEvent;
import me.mrcreepton.minebattle.events.PlayerImmunityEvent;
import me.mrcreepton.minebattle.events.PlayerJoinEvent;
import me.mrcreepton.minebattle.events.TaskEvents;
import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.models.Task;
import me.mrcreepton.minebattle.utils.ChatUtils;
import me.mrcreepton.minebattle.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        PlayerManager.init();
        ItemUtils.init();

        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new TaskEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerImmunityEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);

        getCommand("top").setExecutor(new TopExecutor());
        getCommand("points").setExecutor(new PointsExecutor());
        getCommand("setcontact").setExecutor(new SetContactExecutor());
        getCommand("getcontact").setExecutor(new GetContactExecutor());

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                ScoreboardManager m = Bukkit.getScoreboardManager();
                for (Player player : getServer().getOnlinePlayers())
                {
                    me.mrcreepton.minebattle.models.Player playerModel = PlayerManager.getPlayerByNick(player.getName());
                    if (playerModel != null)
                    {
                        if (playerModel.getImmunity() > 0 && !player.isDead()) {
                            playerModel.setImmunity(playerModel.getImmunity() - 1);
                            if (playerModel.getImmunity() == 0)
                                ChatUtils.sendMessage(player, "Ваш иммунитет закончился");
                        }

                        Scoreboard scoreboard = playerModel.getScoreboard();
                        Objective o = scoreboard.getObjective("k");

                        int i = 0;
                        for (Task task : playerModel.getTaskList())
                        {
                            o.getScore("§b- §6" + task.getDisplayName()).setScore(task.getTotal());
                        }

                        player.setScoreboard(scoreboard);
                    }
                }
            }
        }, 20L, 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
