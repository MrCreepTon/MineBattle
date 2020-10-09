package me.mrcreepton.minebattle.commands;

import me.mrcreepton.minebattle.configs.PlayerConfig;
import me.mrcreepton.minebattle.utils.ChatUtils;
import me.mrcreepton.minebattle.utils.PluginUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class GetContactExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp())
        {
            if (args.length > 0)
            {
                PlayerConfig playerConfig = new PlayerConfig(PluginUtils.getMainInstance());
                Configuration config = playerConfig.getConfig();

                if (config.contains("players." + args[0]))
                {
                    if (config.contains("players." + args[0] + ".url"))
                    {
                        ChatUtils.sendMessage(sender, "Контакт для связи игрока §b" + args[0] + "§f: §6" + config.getString("players." + args[0] + ".url"));
                    }
                    else
                        ChatUtils.sendMessage(sender, "Указанный игрок не привязал контакт для связи.");
                }
                else
                    ChatUtils.sendMessage(sender, "Указанный игрок не существует.");
            }
        }

        return true;
    }
}
