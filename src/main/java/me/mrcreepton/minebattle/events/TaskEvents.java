package me.mrcreepton.minebattle.events;

import me.mrcreepton.minebattle.managers.PlayerManager;
import me.mrcreepton.minebattle.utils.ItemUtils;
import me.mrcreepton.minebattle.utils.PluginUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;

public class TaskEvents implements Listener {

    @EventHandler
    public static void onBreakBlock(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        if (block.getType() == Material.DIAMOND_ORE)
        {
            for (ItemStack drop : block.getDrops())
            {
                if (drop.getType() == Material.DIAMOND_ORE)
                    drop.setType(Material.DIAMOND);
                block.getWorld().dropItemNaturally(block.getLocation(), drop);
            }
            block.setType(Material.AIR);
            PlayerManager.getPlayerTask(e.getPlayer().getName(), "DiamondsFound").onCompleted(e.getPlayer().getName());
        }
    }

    @EventHandler
    public static void onPlayerConsumeEvent(PlayerItemConsumeEvent e)
    {
        ItemStack item = e.getItem();
        if (ItemUtils.isItemFood(item.getType()))
            PlayerManager.getPlayerTask(e.getPlayer().getName(), "FoodEaten").onCompleted(e.getPlayer().getName());
    }

    @EventHandler
    public static void onProjectileThrow(ProjectileHitEvent e)
    {
        if (e.getEntity() instanceof EnderPearl)
            if (e.getEntity().getShooter() instanceof Player) {
                Player player = (Player)e.getEntity().getShooter();
                PlayerManager.getPlayerTask(player.getName(), "Teleports").onCompleted(player.getName());
            }
    }

    @EventHandler
    public static void onEntityKilledByPlayer(EntityDeathEvent e)
    {
        if (e.getEntity().getKiller() != null) {
            Player player = e.getEntity().getKiller();
            if (!(e.getEntity() instanceof Player)) {
                PlayerManager.getPlayerTask(player.getName(), "MobKills").onCompleted(player.getName());
            }
        }
    }

    @EventHandler
    public static void onTreeGrow(StructureGrowEvent e)
    {
        PlayerManager.getPlayerTask(e.getPlayer().getName(), "GrownTrees").onCompleted(e.getPlayer().getName());
    }

    @EventHandler
    public static void onMobTamed(EntityTameEvent e)
    {
        if (e.getOwner() instanceof Player) {
            Player player = (Player)e.getOwner();
            PlayerManager.getPlayerTask(player.getName(), "TamedMobs").onCompleted(player.getName());
        }
    }

    @EventHandler
    public static void onInventoryClicked(InventoryClickEvent e)
    {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player)e.getWhoClicked();
            if (e.getInventory().getType() == InventoryType.FURNACE && e.getSlot() == 2)
                PlayerManager.getPlayerTask(player.getName(), "ItemsCooked").onCompleted(player.getName(), e.getCurrentItem().getAmount());
        }
    }

}
