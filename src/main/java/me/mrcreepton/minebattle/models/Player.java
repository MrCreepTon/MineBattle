package me.mrcreepton.minebattle.models;

import me.mrcreepton.minebattle.managers.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class Player {

    private String name;
    private List<Task> taskList;
    private Scoreboard scoreboard;
    private int immunity = 0;

    public Player(String name) {
        this.name = name;
        this.taskList = TaskManager.getTaskList();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o = this.scoreboard.registerNewObjective("k", "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§bMine§6Battle §f| Cтатистика");
    }

    public int getImmunity() {
        return immunity;
    }

    public void setImmunity(int immunity) {
        this.immunity = immunity;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
