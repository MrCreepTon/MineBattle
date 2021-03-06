package me.mrcreepton.minebattle.commands.points.arguments;

import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.models.Argument;
import me.mrcreepton.minebattle.utils.ChatUtils;
import org.bukkit.command.CommandSender;

public class Remove extends Argument {

    public Remove(CommandSender sender, String[] args) {
        super(sender, args);

        if (args.length > 3)
        {
            try
            {
                int points = Integer.parseInt(args[3]);
                boolean result = PlayerManager.removePointsFromPlayerTask(args[1], args[2], points);
                ChatUtils.sendMessage(sender, result ? "§aОперация успешно выполнена" : "§cОперация не выполнена");
            }
            catch (Exception e)
            {
                ChatUtils.sendMessage(sender, "Некорректное количество очков");
            }
        }
        else
            ChatUtils.sendMessage(sender, "Используйте: §b/points remove §6[Ник] [Таск] [Очки]");
    }
}
