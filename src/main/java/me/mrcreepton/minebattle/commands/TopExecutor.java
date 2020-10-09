package me.mrcreepton.minebattle.commands;

import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.managers.TaskManager;
import me.mrcreepton.minebattle.models.Player;
import me.mrcreepton.minebattle.models.Task;
import me.mrcreepton.minebattle.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TopExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<Task> taskList = TaskManager.getTaskList();

        ChatUtils.sendMessage(sender, "Топ игроков:");

        boolean anotherColor = false;

        for (Task task : taskList)
        {
            anotherColor = !anotherColor;
            Player player = PlayerManager.getTopPlayerInTask(task.getName());
            ChatUtils.sendMessage(sender,  task.getDisplayName() + " - " + (anotherColor ? "§b" : "§6") + player.getName() + " §f(" + String.valueOf(PlayerManager.getPlayerTask(player.getName(), task.getName()).getTotal()) + " §fочков)");
        }

        return true;
    }

}
