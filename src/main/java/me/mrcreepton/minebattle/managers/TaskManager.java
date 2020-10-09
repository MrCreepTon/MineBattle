package me.mrcreepton.minebattle.managers;

import me.mrcreepton.minebattle.models.Task;
import me.mrcreepton.minebattle.tasks.*;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public static List<Task> getTaskList()
    {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new DeathsFromPlayer());
        taskList.add(new DiamondsFound());
        taskList.add(new FoodEaten());
        taskList.add(new GrownTrees());
        taskList.add(new MobKills());
        taskList.add(new PlayerKills());
        taskList.add(new Teleports());
        return taskList;
    }

}
