package com.techiecrow;

import com.techiecrow.listeners.OreCoal;
import org.bukkit.plugin.java.JavaPlugin;

public class OreRewards extends JavaPlugin
{

    // Enable the plugin
    public void onEnable()
    {
        // Register listeners
        this.registerListeners();
    }

    // Register listeners
    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new OreCoal(this), this);
    }
}