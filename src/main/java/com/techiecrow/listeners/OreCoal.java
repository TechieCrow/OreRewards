package com.techiecrow.listeners;

import com.techiecrow.OreRewards;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

import static org.bukkit.GameMode.SURVIVAL;

public class OreCoal implements Listener
{
    private final Random random = new Random();
    private OreRewards plugin;
    private ArrayList<Object> PlayerPlaced = new ArrayList<>();

    private int randomchance = this.plugin.getConfig().getInt("Iron Ore Reward Chance");

    private ItemStack CoalOre1 = new ItemStack(Material.DIAMOND, 1);

    public OreCoal(OreRewards instance)
    {

        plugin = instance;

    }

    private int getRandomNumber()
    {
        return random.nextInt(100) + 1;
    }

    private boolean RandomNumber()
    {
        return randomchance >= getRandomNumber();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockBreak(BlockBreakEvent e)
    {
        if(e.getBlock().getType() == Material.COAL_ORE && RandomNumber() && e.getPlayer().getGameMode() == SURVIVAL)
        {
            if(PlayerPlaced.contains(e.getBlock().getLocation()))
            {

            } else
            {
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), CoalOre1);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockPlace(BlockPlaceEvent e)
    {
        PlayerPlaced.add(e.getBlockPlaced().getLocation());
    }

}