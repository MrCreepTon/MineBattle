package me.mrcreepton.minebattle.events;

import me.mrcreepton.minebattle.configs.PlayerConfig;
import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.models.Player;
import me.mrcreepton.minebattle.utils.ChatUtils;
import me.mrcreepton.minebattle.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public static void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e)
    {
        Player player = PlayerManager.getPlayerByNick(e.getPlayer().getName());
        if (player == null) {
            PlayerManager.registerNewPlayer(e.getPlayer().getName());
        }

        PlayerConfig playerConfig = new PlayerConfig(PluginUtils.getMainInstance());
        Configuration config = playerConfig.getConfig();

        if (!config.contains("players." + e.getPlayer().getName() + ".url"))
        {
            ChatUtils.sendMessage(e.getPlayer(), "§c§lВажно! Важно! Прочти!");
            ChatUtils.sendMessage(e.getPlayer(), "У вас не указан контакт для связи. §cБез него приз не выдадут!");
            ChatUtils.sendMessage(e.getPlayer(), "§cСделайте это прямо сейчас! Команда: /setcontact [URL для связи]");
            ChatUtils.sendMessage(e.getPlayer(), "§c§lURL потом изменить будет нельзя!");
        }
        ChatUtils.sendMessage(e.getPlayer(), "Увидел читера? Оставляй жалобу на него в теме BlastHack (https://www.blast.hk/threads/65937/)");
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent e)
    {
        Player player = PlayerManager.getPlayerByNick(e.getPlayer().getName());
        if (player == null) {
            if (player.getImmunity() > 0)
                player.setImmunity(0);
        }
    }

}
