package me.mrcreepton.minebattle.commands.points.arguments;

import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.models.Argument;
import me.mrcreepton.minebattle.utils.ChatUtils;
import org.bukkit.command.CommandSender;

public class ClearAll extends Argument {

    public ClearAll(CommandSender sender, String[] args) {
        super(sender, args);

        if (args.length > 1)
        {
            boolean result = PlayerManager.clearAllPlayerPoints(args[1]);
            ChatUtils.sendMessage(sender, result ? "§aОперация успешно выполнена" : "§cОперация не выполнена");
        }
        else
            ChatUtils.sendMessage(sender, "Используйте: §b/points clearall §6[Ник]");
    }
}
