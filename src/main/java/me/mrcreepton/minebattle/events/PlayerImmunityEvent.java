package me.mrcreepton.minebattle.events;

import me.mrcreepton.minebattle.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerImmunityEvent implements Listener {

    @EventHandler
    public static void onPlayerGotDamage(EntityDamageEvent e)
    {
        if (e.getEntity() instanceof Player)
        {
            org.bukkit.entity.Player bPlayer = (org.bukkit.entity.Player) e.getEntity();
            me.mrcreepton.minebattle.models.Player player = PlayerManager.getPlayerByNick(bPlayer.getName());
            if (player != null)
            {
                if (player.getImmunity() > 0)
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void onPlayerGiveDamage(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player)
        {
            org.bukkit.entity.Player bPlayer = (org.bukkit.entity.Player) e.getDamager();
            me.mrcreepton.minebattle.models.Player player = PlayerManager.getPlayerByNick(bPlayer.getName());
            if (player != null)
            {
                if (player.getImmunity() > 0) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public static void onPlayerBlockPlaceEvent(BlockPlaceEvent e)
    {
        me.mrcreepton.minebattle.models.Player player = PlayerManager.getPlayerByNick(e.getPlayer().getName());
        if (player != null)
        {
            if (player.getImmunity() > 0)
                e.setCancelled(true);
        }
    }

    @EventHandler
    public static void OnPlayerBlockBreakEvent(BlockBreakEvent e)
    {
        me.mrcreepton.minebattle.models.Player player = PlayerManager.getPlayerByNick(e.getPlayer().getName());
        if (player != null)
        {
            if (player.getImmunity() > 0)
                e.setCancelled(true);
        }
    }

}
