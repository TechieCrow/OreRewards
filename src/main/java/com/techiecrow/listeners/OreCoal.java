package com.techiecrow.listeners;

import com.techiecrow.OreRewards;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

import static org.bukkit.GameMode.SURVIVAL;

public class OreCoal implements Listener
{
    private OreRewards plugin;

    //An array of blocks the player has placed
    private ArrayList<Object> PlayerPlaced = new ArrayList<>();

    public OreCoal(OreRewards instance)
    {

        plugin = instance;

    }

    // Block break event
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockBreak(BlockBreakEvent e)
    {
        // Define an item to put into chest
        ItemStack ds = new ItemStack(Material.DIAMOND_SWORD, 1);

        // check if broken block is == coal ore and player is in survival
        if (e.getBlock().getType() == Material.COAL_ORE && e.getPlayer().getGameMode() == SURVIVAL)
        {
            //Check if player has placed block
            if (PlayerPlaced.contains(e.getBlock().getLocation()))
            {

            }
            // If block spawnned naturally
            else
            {
                // Cancel block break
                e.setCancelled(true);

                int x = e.getBlock().getLocation().getBlockX();
                int y = e.getBlock().getLocation().getBlockY();
                int z = e.getBlock().getLocation().getBlockZ();
                World w = e.getBlock().getLocation().getWorld();

                Location chestLoc = new Location(w, x, y, z);

                // Set block to chest
                chestLoc.getBlock().setType(Material.CHEST);

                // Get chest
                Chest chest = (Chest) chestLoc.getBlock().getState();

                // Get invenotry
                Inventory inv = chest.getInventory();

                // Add item to chest
                inv.setItem(26, ds);

                // Start work on trying to get natrually dropped items into chest too
                Collection<ItemStack> drops = e.getBlock().getDrops();

                for (ItemStack st : drops)
                {
                    inv = chest.getInventory();
                    inv.addItem(st);
                }
                e.getBlock().getDrops().clear();
            }
        }
    }

    // Placed block event (used to check if player placed a block that we broke for the event above)
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockPlace(BlockPlaceEvent e)
    {
        PlayerPlaced.add(e.getBlockPlaced().getLocation());
    }
}