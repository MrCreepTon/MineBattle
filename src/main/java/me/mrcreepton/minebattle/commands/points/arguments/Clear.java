package me.mrcreepton.minebattle.commands.points.arguments;

import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.models.Argument;
import me.mrcreepton.minebattle.utils.ChatUtils;
import org.bukkit.command.CommandSender;

public class Clear extends Argument {

    public Clear(CommandSender sender, String[] args) {
        super(sender, args);

        if (args.length > 2)
        {
            boolean result = PlayerManager.clearPointsFromPlayerTask(args[1], args[2]);
            ChatUtils.sendMessage(sender, result ? "§aОперация успешно выполнена" : "§cОперация не выполнена");
        }
        else
            ChatUtils.sendMessage(sender, "Используйте: §b/points clear §6[Ник] [Таск]");
    }
}
