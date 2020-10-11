package me.mrcreepton.minebattle.models;

import me.mrcreepton.minebattle.managers.PlayerManager;
import org.bukkit.Bukkit;

public class Task {

    private String name;
    private String displayName;
    private int total = 0;

    public Task(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Task(String name, String displayName, int total) {
        this.name = name;
        this.displayName = displayName;
        this.total = total;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void onCompleted(String playerName)
    {
        PlayerManager.addPointsToPlayerTask(playerName, name, 1);
    }

    public void onCompleted(String playerName, int amount)
    {
        PlayerManager.addPointsToPlayerTask(playerName, name, amount);
    }

}
