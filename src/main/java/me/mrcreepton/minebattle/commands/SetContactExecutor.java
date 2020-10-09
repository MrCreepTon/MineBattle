package me.mrcreepton.minebattle.commands;

import me.mrcreepton.minebattle.configs.PlayerConfig;
import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.utils.ChatUtils;
import me.mrcreepton.minebattle.utils.PluginUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SetContactExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player)sender;
            String playerName = player.getName();
            PlayerConfig playerConfig = new PlayerConfig(PluginUtils.getMainInstance());
            Configuration config = playerConfig.getConfig();

            if (!config.contains("players." + playerName + ".url")) {
                if (args.length > 0) {
                    config.set("players." + playerName + ".url", args[0]);
                    playerConfig.saveConfig();
                    ChatUtils.sendMessage(sender, "Установлена ссылка для связи: §b" + args[0]);
                } else
                    ChatUtils.sendMessage(sender, "Используйте: §b/setcontact §6[Ваш §c§lURL §6(VK, BlastHack или еще что-то)]");
            }
            else
                ChatUtils.sendMessage(sender, "Вы уже указали свою ссылку для связи!");
        }
        return true;
    }
}
