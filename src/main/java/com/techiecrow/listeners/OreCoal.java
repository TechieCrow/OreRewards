package com.techiecrow.listeners;

import com.techiecrow.OreRewards;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

    private OreRewards plugin;

    public OreCoal(OreRewards pl) {
        this.plugin = pl;
    }

    private final Random random = new Random();

    private final int getRandomNumber() {
        return random.nextInt(100) + 1;
    }

    private final boolean RandomNumber() {
        return 50 >= getRandomNumber();
    }

    private ArrayList<Object> PlayerPlaced = new ArrayList<>();

    private ItemStack IronOreDrop1 = new ItemStack(Material.DIAMOND, 1);

    @EventHandler(priority = EventPriority.MONITOR)
	public void onBreakEvent(BlockBreakEvent e) {

        Player player = e.getPlayer();

        if(e.getBlock().getType() == Material.COAL_ORE && RandomNumber() && e.getPlayer().getGameMode() == SURVIVAL)
        {
            if(PlayerPlaced.contains(e.getBlock().getLocation()))
            {

            }
            else
            {
                e.getPlayer().sendMessage("Naturally Generated Block!");
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), IronOreDrop1);
            }
        }

	}

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlaceEvent(BlockPlaceEvent e)
    {
        e.getPlayer().sendMessage("Player Placed Block!");
        PlayerPlaced.add(e.getBlockPlaced().getLocation());
    }
}