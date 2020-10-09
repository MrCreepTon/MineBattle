package me.mrcreepton.minebattle.events;

import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.models.Player;
import me.mrcreepton.minebattle.utils.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeathEvent implements Listener {

    @EventHandler
    public static void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent e)
    {
        Player player = PlayerManager.getPlayerByNick(e.getEntity().getName());
        if (player != null) {
            ChatUtils.sendMessage(e.getEntity(), "Вам выдан иммунитет на 15 секунд. Вы не можете пока ничего делать и вам тоже ничео не могут сделать.");
            player.setImmunity(15);
        }
    }

}
