package com.techiecrow;

import com.techiecrow.commands.ReloadCommand;
import com.techiecrow.listeners.OreCoal;
import org.bukkit.plugin.java.JavaPlugin;

public class OreRewards extends JavaPlugin
{
    public void onEnable()
    {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.getCommand("orerewardsreload").setExecutor(new ReloadCommand(this));
        new OreCoal(this);
    }

    public void onDisable()
    {

    }
}