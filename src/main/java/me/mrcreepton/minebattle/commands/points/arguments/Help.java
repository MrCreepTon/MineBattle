package me.mrcreepton.minebattle.commands.points.arguments;

import me.mrcreepton.minebattle.managers.TaskManager;
import me.mrcreepton.minebattle.models.Argument;
import me.mrcreepton.minebattle.models.Task;
import me.mrcreepton.minebattle.utils.ChatUtils;
import org.bukkit.command.CommandSender;

public class Help extends Argument {

    public Help(CommandSender sender, String[] args) {
        super(sender, args);

        ChatUtils.sendMessage(sender, "Команды администратора:");
        ChatUtils.sendMessage(sender, "§b/points help §f- показать эту помощь");
        ChatUtils.sendMessage(sender, "§b/points add §6[Ник] [Таск] [Очки] §f- добавить очков");
        ChatUtils.sendMessage(sender, "§b/points remove §6[Ник] [Таск] [Очки] §f- уменьшить очков");
        ChatUtils.sendMessage(sender, "§b/points set §6[Ник] [Таск] [Очки] §f- установить значение очков");
        ChatUtils.sendMessage(sender, "§b/points clear §6[Ник] [Таск] §f- очистить очки");
        ChatUtils.sendMessage(sender, "§b/points clearall §6[Ник] §f- полное обнуление очков\n");

        String str = "Доступные таски:";
        boolean anotherColor = false;

        for (Task task : TaskManager.getTaskList())
        {
            anotherColor = !anotherColor;
            str = str + (anotherColor ? "§b" : "§6") + " " + task.getName();
        }

        ChatUtils.sendMessage(sender, str);
    }
}
