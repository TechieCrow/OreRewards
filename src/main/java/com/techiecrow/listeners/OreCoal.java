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

    String coalore = this.plugin.getConfig().getString("Coal Ore");

    String coalorereward = this.plugin.getConfig().getString("Coal Ore Rewards");

    int coalorerewardamount = this.plugin.getConfig().getInt("Coal Ore Amount");

    ItemStack IronOreDrop1 = new ItemStack(Material.valueOf(coalorereward), coalorerewardamount);

    @EventHandler(priority = EventPriority.MONITOR)
	public void onBreakEvent(BlockBreakEvent e) {

        Player player = e.getPlayer();

        if(e.getBlock().getType() == Material.valueOf(coalore) && RandomNumber() && e.getPlayer().getGameMode() == SURVIVAL)
        {
            if(PlayerPlaced.contains(e.getBlock().getLocation()))
            {
                return;
            }
            else
            {
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), IronOreDrop1);
            }
        }

	}

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlaceEvent(BlockPlaceEvent e)
    {
        PlayerPlaced.add(e.getBlockPlaced().getLocation());
    }
}